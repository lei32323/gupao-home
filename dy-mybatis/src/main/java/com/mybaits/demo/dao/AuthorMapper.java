package com.mybaits.demo.dao;

import com.mybaits.demo.entity.Author;
import org.apache.ibatis.annotations.CacheNamespace;

public interface AuthorMapper {
    int deleteByPrimaryKey(Integer authorId);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Integer authorId);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);
}