package com.mybaits.demo.factory;

import com.mybaits.demo.entity.Blog;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

public class DyObjectFactory extends DefaultObjectFactory {

    @Override
    public Object create(Class type) {
        if (type.equals(Blog.class)) {
            Blog b = new Blog();
            b.setName("by object factory");
            b.setBid(1111);
            b.setAuthorId(2222);
            return b;
        }
        return super.create(type);
    }
}
