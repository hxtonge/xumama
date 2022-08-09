package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.example.xumama.entity.User;
import com.example.xumama.service.CaidanService;
import com.example.xumama.service.UserService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserController
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Controller
@Slf4j
public class UserController {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(){
        if(StpUtil.isLogin()){
            return "redirect:order";
        }
        return "login";
    }

    @RequestMapping("doLogin")
    public String doLogin(Model model,String username, String password) {
        if(username != null && password != null) {
            if (userService.login(username,password)){
                StpUtil.login(username);
                User user = userService.getUser(username);
                SaSession session = StpUtil.getSession();
                session.set("user",user);
                return "redirect:order";
            }else {
                model.addAttribute("err_msg","账号或密码错误");
                return "error";
            }
        }
        return "login";
    }

    @RequestMapping("logout")
    @SaCheckLogin
    public String logout() {
        StpUtil.logout();
        return "login";
    }
}
