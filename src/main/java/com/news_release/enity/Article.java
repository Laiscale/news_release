package com.news_release.enity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("article")
public class Article {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField("joke_id")
    private String jokeId;
    @TableField("joke_user_id")
    private String jokeUserId;
    @TableField("title")
    private String title;
    @TableField("content")
    private String content;
    @TableField("status")
    private int status;
    @TableField("content_html")
    private String contentHtml;
    @TableField("cover_img")
    private String coverImg;
    @TableField("post_time")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime postTime;
    @TableField("art_like_count")
    private int artLikeCount;
    @TableField("art_comment_count")
    private int artCommentCount;
    @TableField("category")
    private String category;
    @TableField("tags")
    private String tags;

//    private String jokeUserNick;
//    private String jokeUserIcon;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date selfApprovalTime;
}
