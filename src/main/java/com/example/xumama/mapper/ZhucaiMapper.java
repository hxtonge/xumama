package com.example.xumama.mapper;

import com.example.xumama.entity.Zhucai;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  ZhucaiMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface ZhucaiMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Zhucai record);

    int insertSelective(Zhucai record);

    Zhucai selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Zhucai record);

    int updateByPrimaryKey(Zhucai record);

    List<Zhucai> selectAll();
}