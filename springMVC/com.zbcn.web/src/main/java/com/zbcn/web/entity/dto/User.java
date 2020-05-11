package com.zbcn.web.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7001905127567672313L;

    private Long id;

    private Date createTime;
}
