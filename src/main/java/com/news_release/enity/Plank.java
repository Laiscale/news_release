package com.news_release.enity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("plank")
public class Plank {
    private Integer plankId;
    private String content;
    private LocalDateTime sendTime;
}
