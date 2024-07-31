create table course (
    id VARCHAR(255) Primary Key,
    name VARCHAR(255),
    description VARCHAR(255),
    credit INT);

create table author (
    id VARCHAR(255) Primary Key,
    name VARCHAR(255),
    email VARCHAR(255),
    birthdate DATE
);

create table rating (
    id VARCHAR(255) Primary Key,
    number INT
);

create table assessment (
    id VARCHAR(255) Primary Key,
    content VARCHAR(255)
);

create table authoring (
    author_id VARCHAR(255) References author(id),
    course_id VARCHAR(255) References course(id),
    Primary Key(author_id, course_id)
);