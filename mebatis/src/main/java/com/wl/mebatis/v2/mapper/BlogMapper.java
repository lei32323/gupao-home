package com.wl.mebatis.v2.mapper;


import com.wl.mebatis.v2.annotation.Entity;

@Entity(Blog.class)
public interface BlogMapper {

    Blog selectById(Integer bid);

}