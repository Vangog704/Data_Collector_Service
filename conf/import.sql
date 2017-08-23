insert into Usermanager(login,password) values ('admin', 'admin');

insert into Field (TYPE, ISACTIVE, REQUIRED, NAME) values ('simpletext', 'true', 'true', 'Name');

insert into Field (TYPE, ISACTIVE, REQUIRED, NAME) values ('radio', 'true', 'true', 'Sex');
insert into items (item, value) values ('Sex', 'man');
insert into items (item, value) values ('Sex', 'woman');

insert into Field (TYPE, ISACTIVE, REQUIRED, NAME) values ('date', 'true', 'true', 'Date_of_borth');

insert into USERDATA (ID) values (991);
insert into USERDATA_DATA (ID, KEY, VALUE) values (991, 'Name', 'Ivan');
insert into USERDATA_DATA (ID, KEY, VALUE) values (991, 'Sex', 'man');
insert into USERDATA_DATA (ID, KEY, VALUE) values (991, 'Date_of_borth', '1996-09-14');

insert into USERDATA (ID) values (992);
insert into USERDATA_DATA (ID, KEY, VALUE) values (992, 'Name', 'Antot');
insert into USERDATA_DATA (ID, KEY, VALUE) values (992, 'Sex', 'man');
insert into USERDATA_DATA (ID, KEY, VALUE) values (992, 'Date_of_borth', '1988-03-28');

insert into USERDATA (ID) values (993);
insert into USERDATA_DATA (ID, KEY, VALUE) values (993, 'Name', 'Alina');
insert into USERDATA_DATA (ID, KEY, VALUE) values (993, 'Sex', 'woman');
insert into USERDATA_DATA (ID, KEY, VALUE) values (993, 'Date_of_borth', '1990-07-12');

