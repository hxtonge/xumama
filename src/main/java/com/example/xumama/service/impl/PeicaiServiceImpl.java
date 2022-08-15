package com.example.xumama.service.impl;

import com.example.xumama.entity.Peicai;
import com.example.xumama.mapper.PeicaiMapper;
import com.example.xumama.service.PeicaiService;
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
public class PeicaiServiceImpl implements PeicaiService {

    private PeicaiMapper peicaiMapper;

    @Autowired
    public void setPeicaiMapper(PeicaiMapper peicaiMapper) {
        this.peicaiMapper = peicaiMapper;
    }

    @Override
    public void deletePeiCai(String id) {
        peicaiMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public void addPeiCai(Peicai peicai) {
        peicaiMapper.insert(peicai);
    }
}
