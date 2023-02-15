package com.news_release.enity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
@TableName("user")
public class User {

    private int id;
    private String name;
    private String password;
    private String nickname;
    private String userId;
    private String userIcon;
    private String talk;
    private String address;
    private String token;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    private int banned;
    private int isLogin;
}
