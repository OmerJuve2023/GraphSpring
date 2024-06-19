create table if not exists Post
(
    id             serial primary key not null,
    title          varchar(255)       not null,
    summary        text,
    url            varchar(255)       not null,
    date_published timestamp          not null,
    version        int
);