package com.news_release.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 允许的域名列表
        List<String> allowOrigins = Arrays.asList("http://localhost:8080", "http://localhost:8081");

        // 允许的请求头
        List<String> allowHeaders = Arrays.asList("Content-Type", "Authorization");

        // 允许的请求方法
        List<String> allowMethods = Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS");

        String origin = httpRequest.getHeader("Origin");

        if (allowOrigins.contains(origin)) {
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpResponse.setHeader("Access-Control-Allow-Headers", StringUtils.join(allowHeaders, ","));
            httpResponse.setHeader("Access-Control-Allow-Methods", StringUtils.join(allowMethods, ","));
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
        }

        chain.doFilter(request, response);
    }
}

