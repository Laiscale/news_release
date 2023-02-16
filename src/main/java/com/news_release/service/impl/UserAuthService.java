package com.news_release.service.impl;

import com.news_release.enity.User;
import com.news_release.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getPasswordByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录失败，用户名或密码错误！");
        }
        return org.springframework.security.core.userdetails.User   //这里需要返回UserDetails，SpringSecurity会根据给定的信息进行比对
                .withUsername(user.getName())
                .password(user.getPassword())   //直接从数据库取的密码
                .roles(user.getRole())   //用户角色
                .build();
    }
}
