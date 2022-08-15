package com.example.xumama.service.impl;

import com.example.xumama.entity.Tangshui;
import com.example.xumama.mapper.TangshuiMapper;
import com.example.xumama.service.TangshuiService;
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
public class TangshuiServiceImpl implements TangshuiService {

    private TangshuiMapper tangshuiMapper;

    @Autowired
    public void setTangshuiMapper(TangshuiMapper tangshuiMapper) {
        this.tangshuiMapper = tangshuiMapper;
    }

    @Override
    public void deleteTangShui(String id) {
        tangshuiMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public void addTangShui(Tangshui tangshui) {
        tangshuiMapper.insert(tangshui);
    }
}
