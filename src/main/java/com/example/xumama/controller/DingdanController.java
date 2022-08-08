package com.example.xumama.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.xumama.entity.Caidan;
import com.example.xumama.entity.Dingdan;
import com.example.xumama.service.DingdanService;
import com.example.xumama.vo.CaidanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    public void setDingdanService(DingdanService dingdanService) {
        this.dingdanService = dingdanService;
    }

    @RequestMapping("order")
    @SaCheckLogin
    public String order(Model model) throws Exception {
        CaidanVo caidanVo = (CaidanVo) StpUtil.getSession().get("caidan");
        model.addAttribute("zhucais",caidanVo.getZhucais());
        model.addAttribute("qingcais",caidanVo.getQingcais());
        model.addAttribute("peicais",caidanVo.getPeicais());
        model.addAttribute("tangshuis",caidanVo.getTangshuis());
        log.info("order => {}, caidan : {}", StpUtil.getLoginId(),caidanVo);
        return "order";
    }
    //下订单
    @RequestMapping("addOrder")
    @SaCheckLogin
    @ResponseBody
    public boolean addOrder(String zhucai,String qingcai,String peicai,String tangshui) throws Exception {
        log.info("addOrder value : {},{},{},{}",zhucai,qingcai,peicai,tangshui);
        Dingdan dingdan = new Dingdan();
        dingdan.setDingdanZhucai(zhucai);
        dingdan.setDingdanQingcai(qingcai);
        dingdan.setDingdanPeicai(peicai);
        dingdan.setDingdanTangshui(tangshui);
        return dingdanService.addOrder(dingdan);
    }
    //修改订单
    //导出今日账单
    //// TODO: 2022/8/9  判断管理员,判断是否已经存在订单 ,导出订单为可读信息
}
