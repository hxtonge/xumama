package com.example.xumama.service.impl;

import com.example.xumama.entity.Dingdan;
import com.example.xumama.entity.Tangshui;
import com.example.xumama.entity.Zhucai;
import com.example.xumama.mapper.DingdanMapper;
import com.example.xumama.mapper.TangshuiMapper;
import com.example.xumama.mapper.ZhucaiMapper;
import com.example.xumama.service.DingdanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * DingdanService
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Service
public class DingdanServiceImpl implements DingdanService {

    private ZhucaiMapper zhucaiMapper;

    private TangshuiMapper tangshuiMapper;

    private DingdanMapper dingdanMapper;

    @Autowired
    public void setZhucaiMapper(ZhucaiMapper zhucaiMapper) {
        this.zhucaiMapper = zhucaiMapper;
    }

    @Autowired
    public void setTangshuiMapper(TangshuiMapper tangshuiMapper) {
        this.tangshuiMapper = tangshuiMapper;
    }

    @Autowired
    public void setDingdanMapper(DingdanMapper dingdanMapper) {
        this.dingdanMapper = dingdanMapper;
    }

    /**
     * 新增订单
     *
     * @param dingdan 菜
     *
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    @Override
    public boolean addOrder(Dingdan dingdan) throws Exception {
        //查询主菜和糖水价格
        Zhucai zhucai = zhucaiMapper.selectByPrimaryKey(Integer.parseInt(dingdan.getDingdanZhucai()));
        Tangshui tangshui = tangshuiMapper.selectByPrimaryKey(Integer.parseInt(dingdan.getDingdanTangshui()));
        if(zhucai != null && tangshui != null){
            int zhucaiJiage = Integer.parseInt(zhucai.getJiage());
            int tangshuiJiage = Integer.parseInt(tangshui.getJiage());
            dingdan.setDingdanJiage(zhucaiJiage+tangshuiJiage);
            //获取今日日期
            LocalDate date = LocalDate.now();
            LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.of(0,0,0));
            Date date1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            dingdan.setDingdanDate(date1);
            //添加用户
            dingdan.setDingdanUser("zhangshun");
            //新增订单
            dingdanMapper.insert(dingdan);
            return true;
        }else {
            throw new Exception("菜品不存在");
        }
    }
}
