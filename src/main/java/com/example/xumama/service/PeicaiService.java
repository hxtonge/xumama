package com.example.xumama.service;

import com.example.xumama.entity.Peicai;

/**
 * @author zhangshun
 * @date 2022/8/14
 * @apiNote
 */
public interface PeicaiService {
    void deletePeiCai(String id);

    void addPeiCai(Peicai peicai);
}
