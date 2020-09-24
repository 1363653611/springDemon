package com.zbcn.mybatis.mapper.pkg;

import com.zbcn.mybatis.vo.Blog;

import java.util.List;

public interface BlogMapper2 {

    List<Blog> getAllBlogs();

    Blog selectOne(String id);

    int insert(Blog blog);
}
