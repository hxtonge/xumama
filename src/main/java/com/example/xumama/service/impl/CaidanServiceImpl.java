package com.example.xumama.service.impl;

import com.example.xumama.entity.Caidan;
import com.example.xumama.entity.Peicai;
import com.example.xumama.entity.Qingcai;
import com.example.xumama.entity.Tangshui;
import com.example.xumama.entity.Zhucai;
import com.example.xumama.mapper.CaidanMapper;
import com.example.xumama.mapper.PeicaiMapper;
import com.example.xumama.mapper.QingcaiMapper;
import com.example.xumama.mapper.TangshuiMapper;
import com.example.xumama.mapper.ZhucaiMapper;
import com.example.xumama.service.CaidanService;
import com.example.xumama.vo.CaidanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CaidanServiceImpl
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Service
public class CaidanServiceImpl implements CaidanService {

    private CaidanMapper caidanMapper;
    private ZhucaiMapper zhucaiMapper;
    private QingcaiMapper qingcaiMapper;
    private PeicaiMapper peicaiMapper;
    private TangshuiMapper tangshuiMapper;

    @Autowired
    public void setCaidanMapper(CaidanMapper caidanMapper) {
        this.caidanMapper = caidanMapper;
    }
    @Autowired
    public void setZhucaiMapper(ZhucaiMapper zhucaiMapper) {
        this.zhucaiMapper = zhucaiMapper;
    }
    @Autowired
    public void setQingcaiMapper(QingcaiMapper qingcaiMapper) {
        this.qingcaiMapper = qingcaiMapper;
    }
    @Autowired
    public void setPeicaiMapper(PeicaiMapper peicaiMapper) {
        this.peicaiMapper = peicaiMapper;
    }
    @Autowired
    public void setTangshuiMapper(TangshuiMapper tangshuiMapper) {
        this.tangshuiMapper = tangshuiMapper;
    }

    /**
     * 获取今日菜单
     *
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    @Override
    public CaidanVo getCaidan() {
        Caidan caidan = caidanMapper.selectCaidanToday();
        List<Zhucai> zhucais = new ArrayList<>();
        List<Qingcai> qingcais = new ArrayList<>();
        List<Peicai> peicais = new ArrayList<>();
        List<Tangshui> tangshuis = new ArrayList<>();

        zhucaiMapper.selectByPrimaryKey(1);

        return null;
    }

    public static void main(String[] args) {
        //todo
    }
}
