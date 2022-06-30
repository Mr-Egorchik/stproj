create table groups (
    id uuid primary key,
    number varchar(255) not null
);

create table student (
    id uuid primary key,
    age int4 not null,
    email varchar(255) not null,
    name varchar(255) not null,
    phone varchar(255) not null,
    group_id uuid
);

alter table student
    add constraint student_group_fk
    foreign key (group_id) references groups;