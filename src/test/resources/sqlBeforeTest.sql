delete from athenadbschema.customer;
delete from athenadbschema.address;


insert into athenadbschema.address (id,city, contry,flat, house, region, street) values 
('1','ncity', 'ncontry', 'nflat', 'blackPearlShip', 'nmodified', 'nstreet');

insert into athenadbschema.address (id,city, contry, flat, house, region, street) values 
('2','ncity', 'ncontry', 'nflat', 'blackPearlShip', 'nmodified', 'nstreet');

insert into athenadbschema.customer (id,actual_address_id, first_name, last_name, middle_name, registred_address_id, sex) 
values ('1','1', 'jack', 'sparrow', 'captain', '2', 'male');



insert into athenadbschema.address (id,city, contry, flat, house, region, street) values 
('4','zcity', 'zcontry', 'zflat', 'QueenAnnesRevengeShip', 'zmodified', 'zstreet');

insert into athenadbschema.address (id,city, contry, flat, house,  region, street) values 
('3','zcity', 'zcontry',  'zflat', 'QueenAnnesRevengeShip', 'zmodified', 'zstreet');

insert into athenadbschema.customer (id,actual_address_id, first_name, last_name, middle_name, registred_address_id, sex) 
values ('2','3', 'Edward', 'Teach', 'Blackbeard', '4', 'male');