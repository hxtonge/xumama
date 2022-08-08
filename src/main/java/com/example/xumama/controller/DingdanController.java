package com.example.xumama.controller;

import com.example.xumama.entity.Caidan;
import com.example.xumama.entity.Dingdan;
import com.example.xumama.service.DingdanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DingdanController
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Controller
public class DingdanController {

    private DingdanService dingdanService;

    @Autowired
    public void setDingdanService(DingdanService dingdanService) {
        this.dingdanService = dingdanService;
    }

    //下订单
    @RequestMapping("addOrder")
    public boolean addOrder(String zhucai,String peicai,String qingcai,String tangshui) throws Exception {
        Dingdan dingdan = new Dingdan();
        dingdan.setDingdanZhucai(zhucai);
        dingdan.setDingdanPeicai(peicai);
        dingdan.setDingdanQingcai(qingcai);
        dingdan.setDingdanTangshui(tangshui);
        return dingdanService.addOrder(dingdan);
    }
    //修改订单
    //导出今日账单

}
