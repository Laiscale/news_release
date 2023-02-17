package com.news_release.common;

import com.news_release.enity.User;
import com.news_release.mapper.UserMapper;
import com.news_release.service.UserService;
import com.news_release.service.impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserMapper mapper;

//    @Resource
//    PersistentTokenRepository repository;

    //将cookie存在数据库的配置
//    @Bean
//    public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource) {
//        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
//        repository.setDataSource(dataSource);
//        //项目第一次启动时会在数据库中创建一张表来存储cookie，但是之后再启动项目时需要注释掉或者将true改为false
//        repository.setCreateTableOnStartup(false);
//        return repository;
//    }

    //权限赋予
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()   //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/static/**", "/logins", "/loginout", "/register","/user/userUpdate","/article/addlike").permitAll()    //静态资源，使用permitAll
                // 来运行任何人访问（注意一定要放在前面）
                .antMatchers("/user/**").hasAnyRole("user", "admin")
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().hasAnyRole()     //所有请求必须登陆并且是user,admin角色才可以访问（不包含上面的静态资源）
                .and()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userAuthService)  //使用自定义的Service实现类进行验证
                .passwordEncoder(new BCryptPasswordEncoder());   //依然使用BCryptPasswordEncoder
    }


}
