package com.example.xumama.mapper;

import com.example.xumama.entity.Dingdan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    /**
     * 获得用户当日订单
     * @author zhangShun 2022/8/10
     */
    List<Dingdan> selectToDay(@Param("userId") String userId,@Param("date") Date date);
}