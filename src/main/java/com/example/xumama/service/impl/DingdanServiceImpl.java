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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
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
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    @Override
    public boolean addOrder(Dingdan dingdan) throws Exception {
        //查询主菜和糖水价格
        Zhucai zhucai = zhucaiMapper.selectByPrimaryKey(Integer.parseInt(dingdan.getDingdanZhucai()));
        Tangshui tangshui = null;
        if(StrUtil.isNotBlank(dingdan.getDingdanTangshui())){
            tangshui = tangshuiMapper.selectByPrimaryKey(Integer.parseInt(dingdan.getDingdanTangshui()));
        }
        if(zhucai != null){
            int zhucaiJiage = Integer.parseInt(zhucai.getJiage());
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
            return true;
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
        // 所有订单
        List<Dingdan> allOrder = getAllOrder();
        // 通过所用订单获取什么菜有多少份
        Map<String, Integer> stringIntegerMap = caimingAndFenshu(allOrder);
        // 所有主菜
        List<Zhucai> zhucais = zhucaiMapper.selectAll();
        // 最后返回的主菜
        List<Zhucai> target = new ArrayList<>();
        Iterator<String> iter = stringIntegerMap.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            for (Zhucai zhucai : zhucais) {
                if(zhucai.getId().equals(Integer.valueOf(key))){
                    Zhucai zhucainew = new Zhucai();
                    zhucainew.setName(zhucai.getName());
                    zhucainew.setFenshu(stringIntegerMap.get(key));
                    target.add(zhucainew);
                }
            }
        }
        Integer zongjia = 0 ;
        // 计算总价格
        for (Dingdan dingdan : allOrder) {
            zongjia += Integer.valueOf(dingdan.getDingdanJiage());
        }
        total.setZhucaiList(target);
        total.setZongjia(zongjia);
        return total;
    }

    private Map<String,Integer> caimingAndFenshu(List<Dingdan> allOrder){
        // 所有主菜存起来
        List<String> list = new ArrayList<>();
        // 返回一个 map  主菜 ： 份数
        for (Dingdan dingdan : allOrder) {
            list.add(dingdan.getDingdanZhucai());
        }
        // 将主菜和份数对应起来
        Map<String,Integer> map = new HashMap<>();
        for (String string : list) {
            if(map.containsKey(string)) {
                map.put(string, map.get(string).intValue()+1);
            }else {
                map.put(string,Integer.valueOf(1));
            }
        }
        return map ;
    }
}
