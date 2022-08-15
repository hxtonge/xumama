package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.xumama.service.CaidanService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 新增今日菜单
     *
     * @author zhangShun 2022/8/10
     */
    @PostMapping("addCaidan")
    @SaCheckLogin
    public String addCaidan(Model model,String[] zhucai,String[] qingcai,String[] peicai,String[] tangshui){
        if(zhucai!= null && qingcai!= null && peicai!= null){
            caidanService.addCaidan(zhucai,qingcai,peicai,tangshui);
            return "redirect:caidan";
        }else {
            model.addAttribute("err_msg","主菜,青菜,配菜每样至少选一个");
            return "error";
        }
    }
    //查询菜单
    @RequestMapping("caidan")
    @SaCheckLogin
    public String caidan(Model model){
        //所有菜单
        CaidanVo caidan = caidanService.getAllCaidan();
        //今日菜单
        CaidanVo jinriCaidan = caidanService.getCaidan();
        if(caidan != null){
            model.addAttribute("zhucais",caidan.getZhucais());
            model.addAttribute("qingcais",caidan.getQingcais());
            model.addAttribute("peicais",caidan.getPeicais());
            if(caidan.getTangshuis() != null && caidan.getTangshuis().size()>0){
                model.addAttribute("tangshuis",caidan.getTangshuis());
            }
            log.info("caidan => {}, caidan : {}", StpUtil.getLoginId(),caidan);
        }
        if(jinriCaidan != null){
            model.addAttribute("hasCaidan","Y");
            model.addAttribute("jinrizhucais",jinriCaidan.getZhucais());
            model.addAttribute("jinriqingcais",jinriCaidan.getQingcais());
            model.addAttribute("jinripeicais",jinriCaidan.getPeicais());
            if(jinriCaidan.getTangshuis() != null && jinriCaidan.getTangshuis().size()>0){
                model.addAttribute("jinritangshuis",jinriCaidan.getTangshuis());
            }
            log.info("caidan => {}, jinriCaidan : {}", StpUtil.getLoginId(),jinriCaidan);
        }else {
            model.addAttribute("hasCaidan","N");
        }
        return "caidan";
    }

    /**
     * 删除今日菜单,跳转到制作菜单页面
     * @author zhangShun 2022/8/11
     */
    @PostMapping("deleteCaidan")
    @SaCheckLogin
    public String deleteCaidan(){
        caidanService.deleteCaidan();
        return "redirect:caidan";
    }

    //删除菜单,添加各种小配菜
}
