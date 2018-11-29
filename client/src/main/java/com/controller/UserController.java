package com.controller;

import com.model.User;
import com.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *  用户管理的控制器
 *  thymeleaf html页面获取model值用${} Js获取用[[${}]]
 **/
@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    BaseService service;

    /**
     *  设置首页
     **/
    @RequestMapping(path = "/")
    public String index(){
        return "login";
    }

    /**
     *  用户登陆
     **/
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model){
        User u=(User) service.selectOne(request.getParameter("username"));
        model.addAttribute("password",u.getPassWord());
        return "index";
    }

}
