package com.mybaits.test;

import com.mybaits.demo.dao.BlogMapper;
import com.mybaits.demo.dao.BlogMapperExt;
import com.mybaits.demo.entity.Blog;
import com.mybaits.demo.entity.Comment;
import com.mybaits.demo.factory.DyObjectFactory;
import javafx.scene.control.TableRow;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MyBatisTest {

    /**
     * mybatis api方式
     *
     * @throws IOException
     */
    @Test
    public void testStatemnt() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Blog b = sqlSession.selectOne("com.mybaits.demo.dao.BlogMapper.selectByPrimaryKey", 1);
            System.out.println(b.getName());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * mapper的形式
     */
    @Test
    public void testStatemntMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取一个包含事务的session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            System.out.println(blogMapper.selectByPrimaryKey(1).getName());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 插入
     *
     * @throws IOException
     */
    @Test
    public void testInsert() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setBid(6);
            blog.setAuthorId(8);
            blog.setName("王磊");
            Comment comment = new Comment();
            comment.setBid(6);
            comment.setCommentId(1);
            comment.setContent("描述信息");
            blog.setComment(comment);
            int insert = blogMapper.insertByTypHandler(blog);
            System.out.println("修改了" + insert + " 行");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            Blog blog = blogMapper.selectById(6);
            System.out.println(blog);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * $和#的区别
     */
    @Test
    public void testSelectBean() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setName("王磊");
            Blog b = blogMapper.selectByName(blog);
            System.out.println(b.getName());
            Blog bb = blogMapper.selectByName2(blog);
            System.out.println(bb.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 逻辑分页
     *
     * @throws Exception
     */
    @Test
    public void testSelectByRowBounds() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            RowBounds rowBounds = new RowBounds(1, 2);
            List<Blog> blogList = blogMapper.selectPage(rowBounds);
            for (Blog blog : blogList) {
                System.out.println(blog.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * mapper的继承性
     *
     * @throws Exception
     */
    @Test
    public void testMapperExt() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapperExt blogMapperExt = sqlSession.getMapper(BlogMapperExt.class);
            Blog blog = new Blog();
            blog.setName("王磊");
            Blog b = blogMapperExt.selectByName(blog);
            System.out.println(b.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 一对一 正常加载
     *
     * @throws Exception
     */
    @Test
    public void testOneToOne() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectByPrimaryKey(1);
            System.out.println(blog.getAuthor().getAuthorName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 一对一  懒加载
     *
     * @throws Exception
     */
    @Test
    public void testOneToOne2() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectByPrimaryKey2(1);
            System.out.println(blog);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    /**
     * 自定义ObjectFactory
     * @throws Exception
     */
    @Test
    public void testObjectFactory() throws Exception{
        DyObjectFactory factory = new DyObjectFactory();
        Object o = factory.create(Blog.class);
        System.out.println(o);
    }
}
