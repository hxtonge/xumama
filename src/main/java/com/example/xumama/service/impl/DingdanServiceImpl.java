package com.example.xumama.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.example.xumama.entity.Dingdan;
import com.example.xumama.entity.Tangshui;
import com.example.xumama.entity.Total;
import com.example.xumama.entity.Zhucai;
import com.example.xumama.mapper.DingdanMapper;
import com.example.xumama.mapper.TangshuiMapper;
import com.example.xumama.mapper.ZhucaiMapper;
import com.example.xumama.service.DingdanService;
import com.example.xumama.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @author zhangShun 2022/8/8
     */
    @Override
    public void addOrder(Dingdan dingdan) throws Exception {
        //查询主菜和糖水价格
        Zhucai zhucai = zhucaiMapper.selectByPrimaryKey(Integer.parseInt(dingdan.getDingdanZhucai()));
        Tangshui tangshui = null;
        if(StrUtil.isNotBlank(dingdan.getDingdanTangshui())){
            tangshui = tangshuiMapper.selectByPrimaryKey(Integer.parseInt(dingdan.getDingdanTangshui()));
        }
        if(zhucai != null){
            int zhucaiJiage = zhucai.getJiage();
            int tangshuiJiage = 0;
            if(tangshui != null){
                tangshuiJiage = Integer.parseInt(tangshui.getJiage());
            }
            dingdan.setDingdanJiage(zhucaiJiage+tangshuiJiage);
            dingdan.setDingdanDate(DateUtil.getDate());
            //添加用户
            dingdan.setDingdanUser((String) StpUtil.getLoginId());
            //新增订单
            dingdanMapper.insert(dingdan);
        }else {
            throw new Exception("菜品不存在");
        }
    }

    @Override
    public List<Dingdan> getDingdan() {
        String userId = (String) StpUtil.getLoginId();
        return dingdanMapper.selectToDay(userId,DateUtil.getDate());
    }

    @Override
    public void deleteOrder(String id) {
        dingdanMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public List<Dingdan> getAllOrder() {
        return dingdanMapper.selectAllToDay();
    }

    @Override
    public String getLock() {
        return dingdanMapper.getLock();
    }

    @Override
    public void updateLock(String lock) {
        dingdanMapper.updateLock(lock);
    }

    @Override
    public Total total() {
        Total total = new Total();
        //查询当日所有订单的所有主菜信息
        List<Integer> countJiageGroupByZhucai = dingdanMapper.getZhucaiInfoByToday();
        //计算总价格
        int zongjia = countJiageGroupByZhucai.stream().mapToInt(jiage -> jiage).sum();
        total.setZongjia(zongjia);
        return total;
    }
}
