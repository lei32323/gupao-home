package com.mybaits.demo.dao;

import com.mybaits.demo.entity.Blog;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Blog record);

    int insertByTypHandler(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer bid);

    Blog selectById(Integer bid);

    Blog selectByPrimaryKey2(Integer bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    Blog selectByName(Blog record);

    Blog selectByName2(Blog record);

    List<Blog> selectPage(RowBounds rowBounds);
}