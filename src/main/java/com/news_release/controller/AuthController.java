package com.news_release.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news_release.common.Result;
import com.news_release.enity.User;
import com.news_release.mapper.UserMapper;
import com.news_release.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

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
                            HttpServletRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userMapper.getPasswordByUsername(name);
        boolean flag = encoder.matches(password, user.getPassword());
        user.setLastLoginTime(LocalDateTime.now());
        request.getSession().setAttribute("user", user.getId());
        if (flag) {
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
