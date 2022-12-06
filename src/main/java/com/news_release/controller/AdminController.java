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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserMapper userMapper;

    //登录返回结果接口
    @PostMapping("/login")
    public Result<?> login(@RequestParam String name, @RequestParam String password) {
//        Admin admin = adminService.getAdminByName("name","password");
        Admin admin = adminService.loginResult(name, password);
        if (admin == null){
            return Result.error("400", "请求失败");
        }
        return Result.success(admin);
    }

    //用户分页结果以及模糊查询nickname接口
    @GetMapping("/userList")
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

    //删除单个用户(根据user_id)结果接口
    @DeleteMapping("/delete/user")
    public Result<?> deleteUser(@RequestParam("userId") String userId) {
        int deleteUser = adminService.deleteUser(userId);
        if(deleteUser == 0) {
            return Result.error("400", "请求失败");
        }
        return Result.success(deleteUser);
    }

    //删除多个用户(根据user_id)结果接口
    @DeleteMapping("/delete/userList")
    public Result<?> deleteUserList(@RequestParam List<String> userIds) {
        int deleteUserList = adminService.deleteUserList(userIds);
        if(deleteUserList == 0) {
            return Result.error("400", "请求失败");
        }
        return Result.success(deleteUserList);
    }

    //删除单个文章(根据joke_id)结果接口
    @DeleteMapping("/delete/article")
    public Result<?> deleteArticle(@RequestParam("jokeId") String jokeId) {
        int deleteArticle = adminService.deleteArticle(jokeId);
        if(deleteArticle == 0) {
            return Result.error("400", "请求失败");
        }
        return Result.success(deleteArticle);
    }

    //删除多个文章(根据joke_id)结果接口
    @DeleteMapping("/delete/articleList")
    public Result<?> deleteArticleList(@RequestParam List<String> jokeIds) {
        int deleteArticleList = adminService.deleteArticleList(jokeIds);
        if(deleteArticleList == 0) {
            return Result.error("400", "请求失败");
        }
        return Result.success(deleteArticleList);
    }

    //删除单个评论(根据joke_id)结果接口
    @DeleteMapping("/delete/comment")
    public Result<?> deleteComment(@RequestParam("jokeId") String jokeId) {
        int deleteComment = adminService.deleteComment(jokeId);
        if(deleteComment == 0) {
            return Result.error("400", "请求失败");
        }
        return Result.success(deleteComment);
    }

    //删除多个评论(根据comment_id)结果接口
    @DeleteMapping("/delete/commentList")
    public Result<?> deleteCommentList(@RequestParam List<String> commentIds) {
        int deleteCommentList = adminService.deleteCommentList(commentIds);
        if(deleteCommentList == 0) {
            return Result.error("400", "请求失败");
        }
        return Result.success(deleteCommentList);
    }
}
