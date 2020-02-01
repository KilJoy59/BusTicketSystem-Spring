create sequence buses_seq start 1 increment 1;

create sequence drivers_seq start 1 increment 1;

create sequence tickets_seq start 1 increment 1;

create sequence voyages_seq start 1 increment 1;

create table buses (
id int4 not null,
model varchar(255) not null,
number varchar(255) not null,
driver_id int4,
primary key (id)
);

create table drivers (
id int4 not null,
license varchar(255) not null,
name varchar(255) not null,
surname varchar(255) not null,
primary key (id)
);

create table tickets (
id int4 not null,
is_paid boolean,
place int4 not null,
price int4 not null,
voyage_id int4,
primary key (id)
);

create table voyages (
id int4 not null,
number varchar(255) not null,
bus_id int4,
primary key (id)
);

alter table buses
add constraint FKehawq87sfla3c9m3anighxeaf
foreign key (driver_id) references drivers;

alter table tickets
add constraint FKdin8j19rlarg5y3ken9pcjrv7
foreign key (voyage_id) references voyages;

alter table voyages
add constraint FKk5egtj8sb7hvp9vuu5b6224p4
foreign key (bus_id) references buses;