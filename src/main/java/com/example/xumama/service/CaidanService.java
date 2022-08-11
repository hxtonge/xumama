package com.example.xumama.service;

import com.example.xumama.vo.CaidanVo;

/**
 * CaidanService
 *
 * @author zhangshun
 * @date 2022/8/8
 */
public interface CaidanService {
    /**
     * 获取今日菜单
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    CaidanVo getCaidan();

    /**
     * 获取所有菜单
     *
     * @return 返回结果
     * @author zhangShun 2022/8/9
     */
    CaidanVo getAllCaidan();

    void addCaidan(String[] zhucai, String[] qingcai, String[] peicai, String[] tangshui);

    int deleteCaidan();
}
