package com.news_release.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news_release.config.Result;
import com.news_release.enity.Admin;
import com.news_release.mapper.AdminMapper;
import com.news_release.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @PostMapping("/login")
    public Result login(@RequestParam String name, @RequestParam String password) {
//        Admin admin = adminService.getAdminByName("name","password");
        Admin admin = adminService.getAdminByName(name, password);
        if (admin == null){
            return Result.error("400", "请求失败");
        }
        return Result.success(admin);
    }
}
