package com.news_release.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news_release.common.Result;
import com.news_release.enity.Plank;
import com.news_release.mapper.PlankMapper;
import com.news_release.service.PlankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/plank")
public class PlankController {

    @Autowired
    PlankService plankService;

    @Autowired
    PlankMapper plankMapper;
    //发布公告
    @PostMapping("/post")
    public Result<?> post(@RequestParam String content) {
        Plank plank = new Plank();
        plank.setContent(content);
        Random r = new Random();
        plank.setPlankId(r.nextInt(10000));
        plank.setSendTime(LocalDateTime.now());
        if(plank == null) {
            return Result.error("400", "发布失败");
        }
        plankService.save(plank);
        return Result.success("发布成功");
    }

    //公告展示
    @GetMapping("get")
    public Result<?> get() {
        List<Plank> plankList = plankService.list();
        return Result.success(plankList);
    }

    // 删除公告
    @DeleteMapping("delete")
    public Result<?> delete(@RequestParam Number id) {
        QueryWrapper<Plank> queryWrapper = new QueryWrapper();
        queryWrapper.eq("plank_id",id);
        Boolean plankBoolean = plankService.remove(queryWrapper);
        if(plankBoolean){
            return Result.success("删除成功");
        } else {
            return Result.error("404","删除失败");
        }
    }
}
