package com.tianye.service;

import com.tianye.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public void login(Admin admin, String code, HttpSession session);
}
