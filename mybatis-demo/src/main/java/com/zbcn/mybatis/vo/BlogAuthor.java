package com.zbcn.mybatis.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("blogAuthor")
public class BlogAuthor implements Serializable {
    private static final long serialVersionUID = -5653950165805880878L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 作者名称
     */
    private String name;

    /**
     * 性别，1 表示男，0 表示女
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 注册时间
     */
    private Date createTime;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

