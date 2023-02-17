package com.news_release.controller;

import com.news_release.common.Result;
import com.news_release.enity.User;
import com.news_release.mapper.UserMapper;
import com.news_release.service.UserService;
import com.news_release.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    //登录
    @PostMapping("/logins")
    public Result<?> logins(@RequestParam String name, @RequestParam String password,
                            HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userMapper.getPasswordByUsername(name);
        boolean flag = encoder.matches(password, user.getPassword());
        user.setLastLoginTime(LocalDateTime.now());
        if (flag) {
            map.put(name,user.getName());
            map.put(password, user.getPassword());
            String tokenGenerated = JwtUtil.generateToken(map, user.getName());
            String token = "Bearer " + tokenGenerated;
            response.setHeader("Authorization", token);
            return Result.success(user);
        }
        else return Result.error("400", "账户名或密码错误");
    }

    //退出登录
    @PostMapping("/loginout")
    public  Result<?> loginout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Result.success("退出登录成功");
    }

    //注册
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRegistTime(LocalDateTime.now());
        userService.save(user);
        return Result.success("注册成功");
    }
}
