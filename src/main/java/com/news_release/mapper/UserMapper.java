package com.news_release.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.news_release.enity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name = #{name} limit 1")
    User getPasswordByUsername(String name);
}
