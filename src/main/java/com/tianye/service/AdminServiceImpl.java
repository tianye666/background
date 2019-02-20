package com.tianye.service;

import com.tianye.entity.Admin;
import com.tianye.mapper.AdminMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminMapper adminMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public void login(Admin admin, String code, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(admin.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        System.out.println(session.getAttribute("code"));
        System.out.println(code);
        try {
            if(!session.getAttribute("code").toString().equals(code)){
                throw new RuntimeException("验证码不正确");
            }
            subject.login(token);
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException("账号不存在");
        }catch(IncorrectCredentialsException e){
            throw new IncorrectCredentialsException("密码错误");
        }
    }
}
