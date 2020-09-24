package com.zbcn.mybatis.mapper.xml;

import com.zbcn.mybatis.vo.Blog;

import java.util.List;

public interface BlogMapper {

    List<Blog> getAllBlogs();

    Blog selectOne(Integer id);

    int insert(Blog blog);
}
