CREATE TABLE if not exists AUTO_POST (
   id SERIAL PRIMARY KEY,
   text TEXT,
   created TEXT,
   auto_user_id int,
    CONSTRAINT fk_post_user
         FOREIGN KEY(auto_user_id)
   	  REFERENCES auto_user(id)
);