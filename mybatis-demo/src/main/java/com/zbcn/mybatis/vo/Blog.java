package com.zbcn.mybatis.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *  实体类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/16 17:46
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = 4570984474082458374L;
    /**
     * id
     */
    private Long id;

    /**
     * 作者名称
     */
    private String name;

    /**
     * 博客主题
     */
    private String title;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 该字段的值需要自定义的ObjectFactory生成
     */
    private String glob;

    public String getGlob() {
        return glob;
    }

    public void setGlob(String glob) {
        this.glob = glob;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
