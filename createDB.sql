-- we don't know how to generate database nerds (class Database) :(
--CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

--COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';



create sequence hospital_id_seq;

create table Hospital
(
	Id integer not null DEFAULT nextval('hospital_id_seq')
		constraint Hospital_pkey
			primary key,
	Name varchar(256),
	Address varchar(500),
	GpsCoord varchar(30)
		constraint Hospital_ukey1
			unique,
	TotalBeds integer,
	ReservedBeds integer,
	OccupiedBeds integer
)
;

create sequence resource_id_seq;

create table Resource
(
	Id integer not null DEFAULT nextval('resource_id_seq')
		constraint Resource_pkey
			primary key,
	Name varchar(100)
		constraint Resource_ukey1
			unique,
	Unit varchar(20)
)
;

create sequence need_id_seq;

create table Need
(
	Id integer not null DEFAULT nextval('need_id_seq')
		constraint Need_pkey
			primary key,
	HospitalId integer
		constraint NeedHospital_fkey
			references Hospital,
	ResourceId integer
		constraint NeedResource_fkey
			references Resource,
	Quantity integer,
	constraint Need_ukey1
		unique (HospitalId, ResourceId)
)
;

create sequence have_id_seq;

create table Have
(
	Id integer not null DEFAULT nextval('have_id_seq')
		constraint Have_pkey
			primary key,
	HospitalId integer
		constraint HaveHospital_fkey
			references Hospital,
	ResourceId integer
		constraint HaveResource_fkey
			references Resource,
	Quantity integer,
	constraint Have_ukey1
		unique (HospitalId, ResourceId)
)
;

create sequence person_id_seq;

create table Person
(
	Id integer not null DEFAULT nextval('person_id_seq')
		constraint Person_pkey
		primary key,
	Name varchar(100)
)
;

create sequence booking_id_seq;

create table Booking
(
	Id integer not null DEFAULT nextval('booking_id_seq')
		constraint Booking_pkey
		 primary key,
	NrBeds integer,
	PersonId integer
		constraint BookingPerson_fkey
			references Person,
	HospitalId integer
		constraint BookingHospital_fkey
			references Hospital
)
;

create sequence fulfillment_id_seq;

create table Fulfillment
(
	Id integer not null DEFAULT nextval('fulfillment_id_seq')
		constraint Fulfillment_pkey
			primary key,
	Quantity integer,
	Fulfilled boolean default false,
	PersonId integer
		constraint FulfillmentPerson_fkey
			references Person,
	NeedId integer
		constraint FulfillmentNeed_fkey
			references Need
)
;

create sequence personalinventory_id_seq;

create table PersonalInventory
(
	Id integer not null DEFAULT nextval('personalinventory_id_seq')
		constraint PersonalInventory_pkey
			primary key,
	Quantity integer,
	PersonId integer
		constraint PersonalInventoryPerson_fkey
			references Person,
	ResourceId integer
		constraint PersonalInventoryResource_fkey
			references Resource,
	constraint PersonalInventory_ukey1
		unique (PersonId, ResourceId)
)
;

create or replace function f_FulfillNeed() returns trigger
	language plpgsql
as $f_FulfillNeed$
begin
	Update Need
	Set Quantity = Quantity - new.Quantity
	Where Id = new.NeedId;

	return null;
end;
$f_FulfillNeed$
;

create trigger fulfillment_trg_upd_1
	after update on fulfillment
	for each row execute procedure f_fulfillneed();

create or replace function f_ReserveBeds() returns trigger
	language plpgsql
as $f_ReserveBeds$
declare
	AvailableBeds integer;
begin
	select
		(TotalBeds - OccupiedBeds - ReservedBeds) into AvailableBeds
	from Hospital
	where Id = new.HospitalId;

	if new.NrBeds > AvailableBeds
	then
		raise exception 'Requested number of beds not available at the moment';
	else
		update hospital
		set ReservedBeds = ReservedBeds + new.NrBeds;
	end if;

	return new;
end;
$f_ReserveBeds$
;

create trigger booking_trg_ins_1
	before insert on booking
	for each row execute procedure f_ReserveBeds();

--Person default data
insert into Person (Name)
values ('anonymous');

--Resource default data
insert into Resource (Name, Unit)
values
	('Water', 'cup'),
	('Gasoline','barel'),
	('Bandages','meter'),
	('Medicine', 'medkit')
		
		