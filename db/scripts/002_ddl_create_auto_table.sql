CREATE TABLE if not exists AUTO_POST (
   id SERIAL PRIMARY KEY,
   description TEXT,
  created TIMESTAMP,
    photo bytea,
    price text,
   auto_user_id int,
   status text,
    CONSTRAINT fk_post_user
         FOREIGN KEY(auto_user_id)
   	  REFERENCES auto_user(id)
);