package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.example.xumama.entity.Dingdan;
import com.example.xumama.entity.User;
import com.example.xumama.service.CaidanService;
import com.example.xumama.service.DingdanService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        //加载所有订单
        List<Dingdan> allDingdans = dingdanService.getAllOrder();
        model.addAttribute("allDingdans",allDingdans);
        String copyValue = generatorCopyVal(allDingdans);
        model.addAttribute("copyValue",copyValue);
        //查询订单锁
        String lock = dingdanService.getLock();
        model.addAttribute("lock",lock);
        return "order";
    }

    /**
     * 生成复制信息
     * @author zhangShun 2022/8/12
     */
    private String generatorCopyVal(List<Dingdan> allDingdans) {
        StringBuilder stringBuilder = new StringBuilder("");
        if(allDingdans != null && allDingdans.size()>0){
            String h = "\n";
            String k = " ";
            for(Dingdan dingdan : allDingdans){
                stringBuilder.append(dingdan.getZhucaiName()).append(k);
                stringBuilder.append(dingdan.getQingcaiName()).append(k);
                stringBuilder.append(dingdan.getPeicaiName()).append(k);
                stringBuilder.append(dingdan.getTangshuiName()).append(k).append(h);
            }
        }
        return stringBuilder.toString();
    }

    //下订单
    @RequestMapping("addOrder")
    @SaCheckLogin
    public String addOrder(Model model,String zhucai,String qingcai,String peicai,String tangshui,String jialaFlag) throws Exception {
        String lock = dingdanService.getLock();
        if("N".equals(lock)){
            log.info("addOrder value : {},{},{},{}",zhucai,qingcai,peicai,tangshui);
            Dingdan dingdan = new Dingdan();
            dingdan.setDingdanZhucai(zhucai);
            dingdan.setDingdanQingcai(qingcai);
            dingdan.setDingdanPeicai(peicai);
            dingdan.setDingdanTangshui(tangshui);
            dingdan.setJialaFlag(jialaFlag);
            dingdanService.addOrder(dingdan);
            return "redirect:order";
        }else {
            model.addAttribute("err_msg","订单已锁定,不允许下单!请联系管理员解锁");
            return "error";
        }
    }
    //删除订单
    @RequestMapping("deleteOrder")
    @SaCheckLogin
    public String deleteOrder(String id){
        dingdanService.deleteOrder(id);
        return "redirect:order";

    }

    @RequestMapping("updateLock")
    @SaCheckLogin
    public String updateLock(){
        String isLock = dingdanService.getLock();
        String lock = "Y";
        if("Y".equals(isLock)){
            lock = "N";
        }
        dingdanService.updateLock(lock);
        return "redirect:order";
    }
    //导出今日账单
    //// TODO: 2022/8/9  判断管理员,判断是否已经存在订单 ,导出订单为可读信息

    //摇号取餐
    @RequestMapping("yaohaoqucan")
    @SaCheckLogin
    public String yaohaoqucan(Model model){
        //查询订单锁
        String lock = dingdanService.getLock();
        if("N".equals(lock)){
            model.addAttribute("err_msg","订单暂未锁定,待订单锁定后进行摇号取餐");
            return "error";
        } else {
            //查询今日下单的所有用户
            List<Dingdan> allDingdans = dingdanService.getAllOrder();
            Set<String> allUsers = allDingdans.stream().map(Dingdan::getDingdanUserName).collect(Collectors.toSet());
            model.addAttribute("todayAllUser", JSONUtil.toJsonStr(allUsers));
            return "yaohaoqucan";
        }
    }
}
