package com.example.xumama.service;

import com.example.xumama.entity.Tangshui;

/**
 * @author zhangshun
 * @date 2022/8/14
 * @apiNote
 */
public interface TangshuiService {
    void deleteTangShui(String id);

    void addTangShui(Tangshui tangshui);
}
