delete from athenadbschema.customer;
delete from athenadbschema.address;
ALTER SEQUENCE athenadbschema.customer_id_seq RESTART;
ALTER SEQUENCE athenadbschema.address_id_seq RESTART;
