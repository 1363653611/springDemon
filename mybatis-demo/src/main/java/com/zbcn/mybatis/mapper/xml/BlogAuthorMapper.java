package com.zbcn.mybatis.mapper.xml;

import com.zbcn.mybatis.vo.AuthorWithBlog;
import com.zbcn.mybatis.vo.BlogAuthor;

public interface BlogAuthorMapper {

    BlogAuthor getBlogAuthorById(Long id);

    int insert(BlogAuthor author);

    int updateById(BlogAuthor blogAuthor);

    AuthorWithBlog getAuthorWithBlog(Long id);
}
