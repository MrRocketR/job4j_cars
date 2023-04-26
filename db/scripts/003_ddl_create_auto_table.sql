CREATE TABLE if not exists AUTO_POST (
   id SERIAL PRIMARY KEY,
   description TEXT,
    created TIMESTAMP,
    photo bytea,
    price text,
    auto_user_id int,
    status boolean,
    car_id  int REFERENCES cars(id),
    CONSTRAINT fk_post_user
         FOREIGN KEY(auto_user_id)
   	  REFERENCES auto_user(id)
);