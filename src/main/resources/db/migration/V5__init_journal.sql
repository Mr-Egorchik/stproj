create table journal (
    id uuid primary key,
    name varchar(255) not null,
    start int4 not null,
    country varchar(255) not null
);