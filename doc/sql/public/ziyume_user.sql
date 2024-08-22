create table ziyume_user
(
    id             varchar(64)        not null
        primary key,
    username       varchar(32)        not null,
    email          varchar(255)       not null,
    password       varchar(200)       not null,
    avatar         varchar(255),
    nike_name      varchar(32),
    telephone      varchar(32),
    remark         varchar(255),
    sort           integer  default 0,
    creator        varchar(64),
    updater        varchar(64),
    created_at     timestamp(6),
    updated_at     timestamp,
    status_at      smallint default 1 not null,
    deleted_status smallint default 0 not null
);

comment on table ziyume_user is '用户表';

comment on column ziyume_user.username is '账号(用户名)';

comment on column ziyume_user.email is '邮箱';

comment on column ziyume_user.password is '密码';

comment on column ziyume_user.avatar is '头像(地址)';

comment on column ziyume_user.nike_name is '昵称';

comment on column ziyume_user.telephone is '手机';

comment on column ziyume_user.remark is '备注';

comment on column ziyume_user.sort is '排序';

comment on column ziyume_user.creator is '创建者';

comment on column ziyume_user.updater is '更新者';

comment on column ziyume_user.created_at is '创建时间';

comment on column ziyume_user.updated_at is '更新时间';

comment on column ziyume_user.status_at is '帐号启用状态：0->启用；1->禁用';

comment on column ziyume_user.deleted_status is '逻辑删除：0->可用状态；1->删除状态';

alter table ziyume_user
    owner to postgres;

