package com.example.xumama.mapper;

import com.example.xumama.entity.Qingcai;
import org.springframework.stereotype.Repository;

/**
 *  QingcaiMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface QingcaiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Qingcai record);

    int insertSelective(Qingcai record);

    Qingcai selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Qingcai record);

    int updateByPrimaryKey(Qingcai record);
}