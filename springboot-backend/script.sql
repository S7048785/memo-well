create table categories
(
    id         int unsigned auto_increment
        primary key,
    name       varchar(50)   not null comment '分类名称，如：表白、闲置、树洞',
    sort_order int default 0 null comment '排序权重',
    constraint name
        unique (name)
);

create table comments
(
    id         int unsigned auto_increment
        primary key,
    post_id    int unsigned                           not null comment '所属帖子ID',
    user_id    int unsigned                           not null comment '评论者ID',
    parent_id  int unsigned default '0'               null comment '父评论ID（用于回复功能）',
    content    text                                   not null comment '评论内容',
    created_at datetime     default CURRENT_TIMESTAMP null
);

create index idx_parent_id
    on comments (parent_id);

create index idx_post_id
    on comments (post_id);

create table likes
(
    id         int unsigned auto_increment
        primary key,
    user_id    int unsigned                       not null,
    post_id    int unsigned                       not null,
    created_at datetime default CURRENT_TIMESTAMP null,
    constraint uk_user_post
        unique (user_id, post_id)
);

create index idx_post_id
    on likes (post_id);

create table posts
(
    id            int unsigned auto_increment
        primary key,
    user_id       int unsigned                           not null comment '发布者ID',
    category_id   int unsigned                           null comment '分类ID',
    type          tinyint(1)   default 1                 not null comment '1:便签(文字), 2:照片',
    content       text                                   null comment '文字内容',
    image_url     varchar(255)                           null comment '图片存储路径',
    card_color    varchar(20)  default '#FFFFFF'         null comment '卡片背景颜色',
    like_count    int unsigned default '0'               null,
    comment_count int unsigned default '0'               null comment '评论量',
    is_top        tinyint(1)   default 0                 null comment '是否置顶',
    created_at    datetime     default CURRENT_TIMESTAMP null,
    updated_at    datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create index idx_category_id
    on posts (category_id);

create index idx_type_created
    on posts (type, created_at);

create index idx_user_id
    on posts (user_id);

create table users
(
    id         int unsigned auto_increment
        primary key,
    email      varchar(64)                           not null,
    username   varchar(50)                           not null comment '用户名',
    password   varchar(255)                          not null comment '加密后的密码',
    avatar     varchar(255)                          null comment '头像URL',
    bio        varchar(64)                           null comment '简介',
    gender     tinyint(1)  default 0                 null comment '0保密；1男；2女',
    role       varchar(10) default 'user'            null,
    created_at datetime    default CURRENT_TIMESTAMP null,
    updated_at datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint username
        unique (username)
);


