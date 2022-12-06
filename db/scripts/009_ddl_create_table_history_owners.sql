create table history_owners (
 id int primary key,
 car_id int  REFERENCES cars(id),
 driver_id int  REFERENCES drivers(id),
 startAt TIMESTAMP,
 endAt TIMESTAMP
 );