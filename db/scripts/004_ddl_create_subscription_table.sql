create table subscription {
    id serial primary key,
    s_post_id int not null REFERENCES AUTO_POST(id),
    s_user_id int not null REFERENCES AUTO_USER(id)
};