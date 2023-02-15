package com.news_release.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news_release.common.Result;
import com.news_release.enity.Article;
import com.news_release.enity.ArticleComment;
import com.news_release.enity.ArticleLike;
import com.news_release.enity.User;
import com.news_release.mapper.ArticleCommentMapper;
import com.news_release.mapper.ArticleLikeMapper;
import com.news_release.mapper.ArticleMapper;
import com.news_release.mapper.UserMapper;
import com.news_release.service.AdminService;
import com.news_release.service.ArticleService;
import com.news_release.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;
    @Autowired
    ArticleLikeMapper articleLikeMapper;
    @Autowired(required = false)
    UserService userService;

    //用户点赞,用于显示一篇文章的点赞数（不懂是不是这个意思）
    @PostMapping("/userlike")
    public Result<?> userLike(@RequestParam String jokeId) {
        QueryWrapper<ArticleLike> queryWrapper = new QueryWrapper();
        queryWrapper.eq("joke_id", jokeId);
        Integer count = articleLikeMapper.selectCount(queryWrapper);
        String ss = count.toString();
        return Result.success(ss);
    }

    //用户点赞列表展示（可输入用户的user_id，由于user表和articlelike表的userid差的远，做了各总展示）
    @GetMapping("/userlikelist")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<ArticleLike> wrapper = Wrappers.<ArticleLike>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(ArticleLike::getJokeUserId, search);
        }
        IPage<ArticleLike> articleLikeIPage = articleLikeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(articleLikeIPage);
    }

    //用户查看自己所发布的文章
    @GetMapping("/userJoke")
    public Result<?> userJoke(@RequestParam String jokeUserId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getJokeUserId, jokeUserId);
        List<Article> articles = articleMapper.selectList(wrapper);
        return Result.success(articles);
    }

    //查询用户所有信息
    @GetMapping("/userInfo")
    public Result<?> userInfo(@RequestParam String userId){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,userId);
        List<User> info = userMapper.selectList(wrapper);
        return Result.success(info);
    }

    //用户个人信息修改
    @PostMapping("/userUpdate")
    public Result<?> userUpdate(@RequestParam int id,@RequestParam String name,@RequestParam String password,@RequestParam String nickname,
                                @RequestParam String user_icon,@RequestParam String talk,@RequestParam String address) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        User user =new User();
        updateWrapper.eq("id",id);
        user.setName(name);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setUserIcon(user_icon);
        user.setTalk(talk);
        user.setAddress(address);
        userMapper.update(user,updateWrapper);
        Integer rows = userMapper.update(user, updateWrapper);
        return Result.success(rows);
    }
    @PostMapping("/userLogin")
    public Result<?> userLogin(@RequestParam String name,@RequestParam String password){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name",name);
        User user = userMapper.selectOne(updateWrapper);
        if(user == null){
            return Result.error("0","用户名不存在");
        } else if (Objects.equals(password, user.getPassword())){
            user.setIsLogin(1);
            user.setLastLoginTime(new Date());
            userMapper.update(user, updateWrapper);
            return Result.success("登录成功");
        } else {
            return Result.error("0","密码错误");
        }
    }
    @GetMapping("/userLoginOut")
    public Result<?> userLoginOut(@RequestParam String name){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name",name);
        User user = userMapper.selectOne(updateWrapper);
        if(user.getIsLogin() == 0){
            return Result.error("0","无效退出登录");
        }
        user.setIsLogin(0);
        userMapper.update(user,updateWrapper);
        return Result.success("退出登录成功");
    }
    @PostMapping("/userRegistered")
    public Result<?> userRegistered(@RequestParam String name,
                                    @RequestParam String password,
                                    @RequestParam String nickname,
                                    @RequestParam(defaultValue = "") String user_icon,
                                    @RequestParam(defaultValue = "") String talk,
                                    @RequestParam(defaultValue = "") String address) {
        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        user.setName(name);
        queryWrapper.eq("name",name);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setUserIcon(user_icon);
        user.setTalk(talk);
        user.setAddress(address);
        user.setRegistTime(new Date());
        Random myRandow = new Random(123456);
        user.setUserId(Objects.toString(myRandow.nextInt()));
        User use = userMapper.selectOne(queryWrapper);
        if(use == null){
            userMapper.insert(user);
            return Result.success("注册成功");
        } else {
            return Result.error("0","用户名重复");
        }
    }
}
