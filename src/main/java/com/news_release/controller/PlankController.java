package com.news_release.controller;

import com.news_release.common.Result;
import com.news_release.enity.Plank;
import com.news_release.mapper.PlankMapper;
import com.news_release.service.PlankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public Result<?> post(@RequestBody Plank plank) {
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
}
