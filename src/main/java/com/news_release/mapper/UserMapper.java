package com.news_release.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.news_release.enity.Article;
import com.news_release.enity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name = #{name} limit 1")
    User getPasswordByUsername(String name);

    @Select("select a.* from article a left join article_like b on a.joke_id=b.joke_id where a.joke_user_id=#{userId}")
    List<Article> getTargetUserInfo(@Param("userId") String userId);
}
