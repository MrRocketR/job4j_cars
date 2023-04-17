create table if not exists cars (
id serial primary key,
name text,
engine_id int not null REFERENCES engines(id)
);