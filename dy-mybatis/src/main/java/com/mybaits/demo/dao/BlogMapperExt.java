package com.mybaits.demo.dao;

import com.mybaits.demo.entity.Blog;

public interface BlogMapperExt extends BlogMapper {

    /**
     * 根据名称获取文章
     * @param name
     * @return
     */
    Blog selectBlogByName(String name);

}
