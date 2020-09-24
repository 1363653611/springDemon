package com.zbcn.mybatis.mapper.anno;

import com.zbcn.mybatis.vo.Blog;
import org.apache.ibatis.annotations.Select;

public interface BlogMapperAnno {

    @Select("select * from blog where id= #{id}")
    public Blog selectOne(int id);
}
