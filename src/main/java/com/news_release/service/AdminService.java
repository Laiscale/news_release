package com.news_release.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.news_release.enity.Admin;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminService extends IService<Admin> {

    Admin getAdminByName(String name, String password);
}
