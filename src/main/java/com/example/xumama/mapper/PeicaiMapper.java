package com.example.xumama.mapper;

import com.example.xumama.entity.Peicai;
import org.springframework.stereotype.Repository;

/**
 *  PeicaiMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface PeicaiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Peicai record);

    int insertSelective(Peicai record);

    Peicai selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Peicai record);

    int updateByPrimaryKey(Peicai record);
}