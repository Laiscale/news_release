package com.news_release.controller;

import com.news_release.common.Result;
import com.news_release.enity.User;
import com.news_release.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class BannerController {

    @Autowired
    UserService userService;

    @PostMapping("/banner/{id}")
    public Result<?> banner(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user.getBanned() == 0) {
            user.setBanned(1);
        }
        if (user.getBanned() == 1) {
            user.setBanned(0);
        }
        boolean flag = userService.updateById(user);
        if (flag) {
            return Result.success("修改成功");
        }
        else return Result.error("400","修改失败");
    }
}
