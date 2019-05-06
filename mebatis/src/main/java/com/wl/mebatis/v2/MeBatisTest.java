package com.wl.mebatis.v2;

import com.wl.mebatis.v2.mapper.Blog;
import com.wl.mebatis.v2.mapper.BlogMapper;
import com.wl.mebatis.v2.session.DefaultSqlSession;
import com.wl.mebatis.v2.session.SqlSessionFactory;

public class MeBatisTest {

    public static void main(String[] args) {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory().build();

        DefaultSqlSession sqlSession = sqlSessionFactory.openSqlSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        System.out.println("==========第一次查询");
        Blog blog = blogMapper.selectById(1);
        System.out.println(blog);

        System.out.println("==========第二次查询");
        Blog blog1 = blogMapper.selectById(1);
        System.out.println(blog1);
    }
}
