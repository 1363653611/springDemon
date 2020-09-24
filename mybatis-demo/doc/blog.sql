drop table if exists blog;
create table blog
(
    ID          BIGINT primary key not null auto_increment comment '主键',
    NAME        varchar(100) default null comment '创建人名称',
    TITLE       varchar(200) comment '博客主题',
    CONTENT     varchar(200) comment '博客内容',
    AUTHOR_ID   BIGINT             NOT NULL COMMENT '作者id',
    CREATE_TIME TIMESTAMP comment '创建时间',
    UPDATE_TIME TIMESTAMP COMMENT '更新时间'
) engine = InnoDB
  default charset = 'utf8' comment '博客表';


drop table if exists blog_author;
create table blog_author
(
    ID          BIGINT primary key not null auto_increment comment '主键',
    NAME        VARCHAR(100) COMMENT '作者名称',
    GENDER      CHAR(1) DEFAULT '1' COMMENT '性别，1 表示男，0 表示女',
    AGE         INT COMMENT '年龄',
    CREATE_TIME TIMESTAMP COMMENT '注册时间'
) engine = InnoDB
  default charset = 'utf8' comment '作者表';