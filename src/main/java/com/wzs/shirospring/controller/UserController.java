package com.wzs.shirospring.controller;

import com.wzs.shirospring.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/loginOK")
    @ResponseBody
    public String loginOK(){
         return "you can see this page ,until you login success";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin success";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "403";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null){
            subject.logout();
        }
        return "login";
    }


    @RequestMapping("/loginUser")
    public String loginUser(String username,
                            String password,
                            boolean remenberme,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            token.setRememberMe(remenberme); // 从前端获取值判断是否需要记住我
            subject.login(token); //登录验证
            User user = new User();
            user.setUsername((String)subject.getPrincipal());
            session.setAttribute("user",user);
            return "index";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "login";
    }




}
