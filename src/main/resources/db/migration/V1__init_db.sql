create table groups (
    uuid uuid not null,
    number varchar(255) not null,
    primary key (uuid)
);

create table student (
    uuid uuid not null,
    age int4 not null,
    email varchar(255) not null,
    name varchar(255) not null,
    phone varchar(255) not null,
    group_id uuid, primary key (uuid)
);

alter table student
    add constraint student_group_fk
    foreign key (group_id) references groups;