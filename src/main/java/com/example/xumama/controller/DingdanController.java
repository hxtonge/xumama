package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.xumama.entity.Caidan;
import com.example.xumama.entity.Dingdan;
import com.example.xumama.entity.User;
import com.example.xumama.service.CaidanService;
import com.example.xumama.service.DingdanService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * DingdanController
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Controller
@Slf4j
public class DingdanController {

    private DingdanService dingdanService;
    private CaidanService caidanService;

    @Autowired
    public void setDingdanService(DingdanService dingdanService) {
        this.dingdanService = dingdanService;
    }

    @Autowired
    public void setCaidanService(CaidanService caidanService) {
        this.caidanService = caidanService;
    }

    @RequestMapping("order")
    @SaCheckLogin
    public String order(Model model) {
        User user = (User) StpUtil.getSession().get("user");
        if(user != null){
            log.info("admin => to order");
            model.addAttribute("isAdmin",user.getIsAdmin());
        }
        CaidanVo caidanVo = caidanService.getCaidan();//获得今日菜单
        List<Dingdan> dingdans = dingdanService.getDingdan();//获得我的订单
        if(caidanVo != null){
            model.addAttribute("zhucais",caidanVo.getZhucais());
            model.addAttribute("qingcais",caidanVo.getQingcais());
            model.addAttribute("peicais",caidanVo.getPeicais());
            if(caidanVo.getTangshuis() != null && caidanVo.getTangshuis().size()>0){
                model.addAttribute("tangshuis",caidanVo.getTangshuis());
            }
            log.info("order => {}, caidan : {}", StpUtil.getLoginId(),caidanVo);
        }else {
            model.addAttribute("announcement","管理员暂未配置今日菜单,请联系管理员");
        }
        if(dingdans != null && dingdans.size()>0){
            log.info("dingdans : {}",dingdans);
            model.addAttribute("dingdans",dingdans);
        }
        return "order";
    }
    //下订单
    @RequestMapping("addOrder")
    @SaCheckLogin
    public String addOrder(String zhucai,String qingcai,String peicai,String tangshui) throws Exception {
        log.info("addOrder value : {},{},{},{}",zhucai,qingcai,peicai,tangshui);
        Dingdan dingdan = new Dingdan();
        dingdan.setDingdanZhucai(zhucai);
        dingdan.setDingdanQingcai(qingcai);
        dingdan.setDingdanPeicai(peicai);
        dingdan.setDingdanTangshui(tangshui);
         dingdanService.addOrder(dingdan);
        return "redirect:order";

    }
    //删除订单
    @RequestMapping("deleteOrder")
    @SaCheckLogin
    public String deleteOrder(String id){
        dingdanService.deleteOrder(id);
        return "redirect:order";

    }
    //导出今日账单
    //// TODO: 2022/8/9  判断管理员,判断是否已经存在订单 ,导出订单为可读信息
}
