package com.smdm.dao;

import com.smdm.bean.Lot;
import com.smdm.bean.LotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotMapper {
    long countByExample(LotExample example);

    int deleteByExample(LotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Lot record);

    int insertSelective(Lot record);

    List<Lot> selectByExample(LotExample example);

    Lot selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Lot record, @Param("example") LotExample example);

    int updateByExample(@Param("record") Lot record, @Param("example") LotExample example);

    int updateByPrimaryKeySelective(Lot record);

    int updateByPrimaryKey(Lot record);
}