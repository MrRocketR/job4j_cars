create table cars (
id int primary key,
name text,
engine_id int not null unique  REFERENCES engines(id)
);