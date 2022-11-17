CREATE TABLE if not exists AUTO_USER (
   id SERIAL PRIMARY KEY,
   login varchar unique,
   password TEXT
);

