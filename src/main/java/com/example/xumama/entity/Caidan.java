package com.example.xumama.entity;

import java.util.Date;
import lombok.Data;

/**
 *  Caidan
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Data
public class Caidan {
    /**
    * 菜单日期
    */
    private Date caidanDate;

    /**
    * 主菜
    */
    private String caidanZhucai;

    /**
    * 青菜
    */
    private String caidanQingcai;

    /**
    * 配菜
    */
    private String caidanPeicai;

    /**
    * 糖水
    */
    private String caidanTangshui;
}