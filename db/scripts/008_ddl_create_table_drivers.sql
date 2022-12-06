 create table drivers (
 id int primary key,
 name text,
 user_id int not null unique  REFERENCES AUTO_USER(id)
 );