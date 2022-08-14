package com.example.xumama.service;

import com.example.xumama.entity.Zhucai;

/**
 * @author zhangshun
 * @date 2022/8/14
 * @apiNote
 */
public interface ZhucaiService {
    void addZhuCai(Zhucai zhucai);

    void deleteZhuCai(String id);
}
