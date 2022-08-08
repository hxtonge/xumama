package com.example.xumama.vo;

import com.example.xumama.entity.Peicai;
import com.example.xumama.entity.Qingcai;
import com.example.xumama.entity.Tangshui;
import com.example.xumama.entity.Zhucai;
import lombok.Data;

import java.util.List;

/**
 * CaidanVo
 *
 * @author zhangshun
 * @date 2022/8/8
 */
@Data
public class CaidanVo {

    private List<Zhucai> zhucais;

    private List<Qingcai> qingcais;

    private List<Peicai> peicais;

    private List<Tangshui> tangshuis;
}
