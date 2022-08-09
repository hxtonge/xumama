package com.example.xumama.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.xumama.service.CaidanService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CaidanController
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Controller
@Slf4j
public class CaidanController {

    private CaidanService caidanService;
    @Autowired
    public void setCaidanService(CaidanService caidanService) {
        this.caidanService = caidanService;
    }

    //新增主菜
    //新增青菜
    //新增配菜
    //新增今日菜单
    @RequestMapping("addCaidan")
    public String addCaidan(){




        return "";
    }
    //查询菜单
    @RequestMapping("caidan")
    public String caidan(Model model){
        CaidanVo caidanVo1 = caidanService.getAllCaidan();
        CaidanVo caidanVo2 = caidanService.getCaidan();
        if(caidanVo1 != null){
            model.addAttribute("zhucais",caidanVo1.getZhucais());
            model.addAttribute("qingcais",caidanVo1.getQingcais());
            model.addAttribute("peicais",caidanVo1.getPeicais());
            model.addAttribute("tangshuis",caidanVo1.getTangshuis());
            log.info("caidan => {}, caidan1 : {}", StpUtil.getLoginId(),caidanVo1);
        }
        if(caidanVo2 != null){
            model.addAttribute("hasCaidan","Y");
            model.addAttribute("jinrizhucais",caidanVo2.getZhucais());
            model.addAttribute("jinriqingcais",caidanVo2.getQingcais());
            model.addAttribute("jinripeicais",caidanVo2.getPeicais());
            model.addAttribute("jinritangshuis",caidanVo2.getTangshuis());
            log.info("caidan => {}, caidan2 : {}", StpUtil.getLoginId(),caidanVo2);
        }else {
            model.addAttribute("hasCaidan","N");
        }
        return "caidan";
    }
}
