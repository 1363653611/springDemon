package com.zbcn.mybatis.vo;

import java.io.Serializable;
import java.util.List;

/**
 *  作者信息包含blog
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/23 10:19
 */
public class AuthorWithBlog extends BlogAuthor implements Serializable {
    private static final long serialVersionUID = 8536168575068687089L;

    private List<Blog> blogs;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
