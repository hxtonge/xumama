package com.example.xumama.mapper;

import com.example.xumama.entity.Dingdan;
import org.springframework.stereotype.Repository;

/**
 *  DingdanMapper
 *
 *  @author zhangshun
 *  @date 2022/8/8
 */
@Repository
public interface DingdanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dingdan record);

    int insertSelective(Dingdan record);

    Dingdan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dingdan record);

    int updateByPrimaryKey(Dingdan record);
}