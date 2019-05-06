package com.wl.mebatis.v1;

public class MeBatisTest {
    public static void main(String[] args) {
        SqlSession sqlSession = new SqlSession(new Exceutor(),new Configuration());

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = mapper.selectById(1);

        System.out.println(blog);
    }
}
