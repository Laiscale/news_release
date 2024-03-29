package com.news_release.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news_release.common.Result;
import com.news_release.enity.User;
import com.news_release.mapper.UserMapper;
import com.news_release.service.UserService;
import com.news_release.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
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
    public Result<?> register(User user) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("name",user.getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRegistTime(LocalDateTime.now());
        Random r = new Random();
        user.setUserId(String.valueOf(r.nextInt(10000)));
        userService.save(user);
        return Result.success("注册成功");
//        Random myRandow = new Random(123456);
//        user.setUserId(Objects.toString(myRandow.nextInt()));
//        User use = userMapper.selectOne(queryWrapper);
//        if(use == null){
//            userService.save(user);
//            return Result.success("注册成功");
//        } else {
//            return Result.error("0","用户名重复");
//        }

    }
}
