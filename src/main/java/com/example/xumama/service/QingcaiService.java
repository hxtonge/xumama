package com.example.xumama.service;

import com.example.xumama.entity.Qingcai;

/**
 * @author zhangshun
 * @date 2022/8/14
 * @apiNote
 */
public interface QingcaiService {
    void deleteQingCai(String id);

    void addQingCai(Qingcai qingcai);
}
