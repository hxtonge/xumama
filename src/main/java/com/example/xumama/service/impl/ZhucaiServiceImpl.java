package com.example.xumama.service.impl;

import com.example.xumama.entity.Zhucai;
import com.example.xumama.mapper.ZhucaiMapper;
import com.example.xumama.service.ZhucaiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangshun
 * @date 2022/8/14
 * @apiNote
 */
@Service
@Slf4j
public class ZhucaiServiceImpl implements ZhucaiService {

    private ZhucaiMapper zhucaiMapper;

    @Autowired
    public void setZhucaiMapper(ZhucaiMapper zhucaiMapper) {
        this.zhucaiMapper = zhucaiMapper;
    }

    @Override
    public void addZhuCai(Zhucai zhucai) {
        zhucaiMapper.insert(zhucai);
    }

    @Override
    public void deleteZhuCai(String id) {
        zhucaiMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }
}
