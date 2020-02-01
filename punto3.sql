drop table if exists Device;
drop table if exists Bus;
drop table if exists DeviceType;
drop table if exists Concessionaire;

create table Concessionaire (
	id serial primary key not null,
	name varchar(50) not null
);

create table DeviceType (
	id serial primary key not null,
	name varchar(50) not null
);

create table Bus(
	id serial primary key not null,
	type varchar(100) not null,
	motor varchar(50),
	brakes varchar(50),
	concessionaireId integer not null,
	constraint bus_concessionaireId_fkey foreign key (concessionaireId)
      references Concessionaire (id) match simple
);

create table Device (
	id serial primary key not null,
	ip varchar(15) not null,
	deviceTypeId integer not null,
	busId integer not null,
	status boolean,
	constraint device_deviceTypeId_fkey foreign key (deviceTypeId)
      references DeviceType (id) match simple,
	constraint device_busId_fkey foreign key (busId)
      references Bus (id) match simple
);

insert into Concessionaire(name) values ('Concessionaire 1');
insert into Concessionaire(name) values ('Concessionaire 2');
insert into Concessionaire(name) values ('Concessionaire 3');
insert into DeviceType(name) values ('CANBUS');
insert into DeviceType(name) values ('NVR');
insert into Bus(type,motor,brakes,concessionaireId) values ('Bi-articulado','dissel','hidraulico',1);
insert into Bus(type,motor,brakes,concessionaireId) values ('Bi-articulado','dissel','hidraulico',1);
insert into Bus(type,motor,brakes,concessionaireId) values ('articulado','gas','hidraulico',2);
insert into Bus(type,motor,brakes,concessionaireId) values ('articulado','gas','hidraulico',2);
insert into Bus(type,motor,brakes,concessionaireId) values ('Bi-articulado','dissel','hidraulico',3);
insert into Bus(type,motor,brakes,concessionaireId) values ('Bi-articulado','gas','hidraulico',3);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.2',1,1,True);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.3',1,2,True);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.21',2,2,True);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.23',1,3,True);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.22',1,4,False);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.52',2,4,True);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.12',1,5,True);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.52',1,6,False);
insert into Device(ip,deviceTypeId,busId,status) values ('123.123.1.29',2,6,True);