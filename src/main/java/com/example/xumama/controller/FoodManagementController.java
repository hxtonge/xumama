package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.example.xumama.entity.Peicai;
import com.example.xumama.entity.Qingcai;
import com.example.xumama.entity.Tangshui;
import com.example.xumama.entity.Zhucai;
import com.example.xumama.service.CaidanService;
import com.example.xumama.service.PeicaiService;
import com.example.xumama.service.QingcaiService;
import com.example.xumama.service.TangshuiService;
import com.example.xumama.service.ZhucaiService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangshun
 * @date 2022/8/14
 * @apiNote 菜品管理
 */
@Controller
@Slf4j
public class FoodManagementController {

    private CaidanService caidanService;

    private ZhucaiService zhucaiService;

    private QingcaiService qingcaiService;

    private PeicaiService peicaiService;

    private TangshuiService tangshuiService;

    @Autowired
    public void setCaidanService(CaidanService caidanService) {
        this.caidanService = caidanService;
    }

    @Autowired
    public void setZhucaiService(ZhucaiService zhucaiService) {
        this.zhucaiService = zhucaiService;
    }

    @Autowired
    public void setQingcaiService(QingcaiService qingcaiService) {
        this.qingcaiService = qingcaiService;
    }

    @Autowired
    public void setPeicaiService(PeicaiService peicaiService) {
        this.peicaiService = peicaiService;
    }

    @Autowired
    public void setTangshuiService(TangshuiService tangshuiService) {
        this.tangshuiService = tangshuiService;
    }

    @RequestMapping("foodManagement")
    @SaCheckLogin
    public String foodManagement(Model model){
        CaidanVo caidan = caidanService.getAllCaidan();
        if(caidan != null){
            model.addAttribute("zhucais",caidan.getZhucais());
            model.addAttribute("qingcais",caidan.getQingcais());
            model.addAttribute("peicais",caidan.getPeicais());
            if(caidan.getTangshuis() != null && caidan.getTangshuis().size()>0){
                model.addAttribute("tangshuis",caidan.getTangshuis());
            }
            log.info("foodManagement => {}, caidan : {}", StpUtil.getLoginId(),caidan);
        }

        return "foodManagement";
    }

    /**
     * 新增主菜
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("addZhuCai")
    @SaCheckLogin
    public String addZhuCai(Model model,Zhucai zhucai){
        zhucaiService.addZhuCai(zhucai);
        return "redirect:foodManagement";
    }

    /**
     * 删除主菜
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("deleteZhuCai")
    @SaCheckLogin
    public String deleteZhuCai(Model model,String id){
        if(StrUtil.isNotBlank(id)){
            zhucaiService.deleteZhuCai(id);
            return "redirect:foodManagement";
        }else {
            model.addAttribute("err_msg","id is null");
            return "error";
        }
    }

    /**
     * 新增青菜
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("addQingCai")
    @SaCheckLogin
    public String addQingCai(Model model, Qingcai qingcai){
        qingcaiService.addQingCai(qingcai);
        return "redirect:foodManagement";
    }

    /**
     * 删除青菜
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("deleteQingCai")
    @SaCheckLogin
    public String deleteQingCai(Model model,String id){
        if(StrUtil.isNotBlank(id)){
            qingcaiService.deleteQingCai(id);
            return "redirect:foodManagement";
        }else {
            model.addAttribute("err_msg","id is null");
            return "error";
        }
    }

    /**
     * 新增配菜
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("addPeiCai")
    @SaCheckLogin
    public String addPeiCai(Model model, Peicai peicai){
        peicaiService.addPeiCai(peicai);
        return "redirect:foodManagement";
    }

    /**
     * 删除配菜
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("deletePeiCai")
    @SaCheckLogin
    public String deletePeiCai(Model model,String id){
        if(StrUtil.isNotBlank(id)){
            peicaiService.deletePeiCai(id);
            return "redirect:foodManagement";
        }else {
            model.addAttribute("err_msg","id is null");
            return "error";
        }
    }

    /**
     * 新增糖水
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("addTangShui")
    @SaCheckLogin
    public String addTangShui(Model model, Tangshui tangshui){
        tangshuiService.addTangShui(tangshui);
        return "redirect:foodManagement";
    }

    /**
     * 删除糖水
     *
     * @author zhangShun 2022/8/14
     */
    @PostMapping("deleteTangShui")
    @SaCheckLogin
    public String deleteTangShui(Model model,String id){
        if(StrUtil.isNotBlank(id)){
            tangshuiService.deleteTangShui(id);
            return "redirect:foodManagement";

        }else {
            model.addAttribute("err_msg","id is null");
            return "error";
        }
    }

}
