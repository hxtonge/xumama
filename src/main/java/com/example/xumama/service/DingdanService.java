package com.example.xumama.service;

import com.example.xumama.entity.Dingdan;

import java.util.List;

/**
 * DingdanService
 *
 * @author zhangshun
 * @date 2022/8/8
 */
public interface DingdanService {

    /**
     * 新增订单
     *
     * @param dingdan 描述
     * @return 返回结果
     * @author zhangShun 2022/8/8
     */
    boolean addOrder(Dingdan dingdan) throws Exception;

    List<Dingdan> getDingdan();

    void deleteOrder(String id);
}
