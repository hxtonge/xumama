package com.example.xumama.mapper;

import com.example.xumama.entity.Peicai;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  PeicaiMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface PeicaiMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Peicai record);

    int insertSelective(Peicai record);

    Peicai selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Peicai record);

    int updateByPrimaryKey(Peicai record);

    List<Peicai> selectAll();
}