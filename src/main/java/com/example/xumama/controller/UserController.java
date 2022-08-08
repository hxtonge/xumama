package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.xumama.service.CaidanService;
import com.example.xumama.service.UserService;
import com.example.xumama.vo.CaidanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UserController
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Controller
public class UserController {

    private UserService userService;

    private CaidanService caidanService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCaidanService(CaidanService caidanService) {
        this.caidanService = caidanService;
    }

    @RequestMapping("/")
    public String index(){
        if(StpUtil.isLogin()){
            return "order";
        }
        return "login";
    }

    @RequestMapping("doLogin")
    public String doLogin(Model model,String username, String password) {
        if(userService.login(username,password)) {
            CaidanVo caidanVo = caidanService.getCaidan();//获得今日菜单
            model.addAttribute("zhucais",caidanVo.getZhucais());
            model.addAttribute("qingcais",caidanVo.getQingcais());
            model.addAttribute("peicais",caidanVo.getPeicais());
            model.addAttribute("tangshuis",caidanVo.getTangshuis());
            StpUtil.login(username);
            return "order";
        }
        return "error";
    }

    @RequestMapping("logout")
    @SaCheckLogin
    public String logout() {
        StpUtil.logout();
        return "login";
    }
}
