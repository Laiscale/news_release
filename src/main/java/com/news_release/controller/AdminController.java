package com.news_release.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news_release.config.Result;
import com.news_release.enity.Admin;
import com.news_release.enity.User;
import com.news_release.mapper.UserMapper;
import com.news_release.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestParam String name, @RequestParam String password) {
//        Admin admin = adminService.getAdminByName("name","password");
        Admin admin = adminService.loginResult(name, password);
        if (admin == null){
            return Result.error("400", "请求失败");
        }
        return Result.success(admin);
    }

    @GetMapping("/userlist")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickname, search);
        }
        IPage<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
