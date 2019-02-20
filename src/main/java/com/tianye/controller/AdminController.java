package com.tianye.controller;

import com.tianye.entity.Admin;
import com.tianye.service.AdminService;
import com.tianye.util.CreateValidateCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    public String login(Admin admin, String code, HttpSession session){
        try {
            adminService.login(admin,code,session);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage",e.getMessage());
            return "redirect:/login.jsp";
        }
        return "redirect:/main/main.jsp";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    @RequestMapping("code")
    public void code(HttpServletResponse response, HttpSession session) throws IOException {
        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();

        // 存 随机字符到  --- session
        session.setAttribute("code", code.toLowerCase());

        // 调用工具类，把生成的随机字符，使用输出流往client输出成图片
        cvc.write( response.getOutputStream());
    }

}
