package com.example.xumama.service.impl;

import com.example.xumama.entity.Qingcai;
import com.example.xumama.mapper.QingcaiMapper;
import com.example.xumama.service.QingcaiService;
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
public class QingcaiServiceImpl implements QingcaiService {

    private QingcaiMapper qingcaiMapper;

    @Autowired
    public void setQingcaiMapper(QingcaiMapper qingcaiMapper) {
        this.qingcaiMapper = qingcaiMapper;
    }

    @Override
    public void deleteQingCai(String id) {
        qingcaiMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public void addQingCai(Qingcai qingcai) {
        qingcaiMapper.insert(qingcai);
    }
}
