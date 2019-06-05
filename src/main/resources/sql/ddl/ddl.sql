create table if not exists roles
(
	id bigserial not null
		constraint roles_pkey
			primary key,
	name varchar(60)
		constraint uk_nb4h0p6txrmfc0xbrd1kglp9t
			unique
);

alter table roles owner to postgres;

create table if not exists route
(
	id bigserial not null
		constraint route_pkey
			primary key,
	dept_place varchar(255),
	dest_place varchar(255)
);

alter table route owner to postgres;

create table if not exists tour_type
(
	id bigserial not null
		constraint tour_type_pkey
			primary key,
	tour_type_name varchar(60)
		constraint uk_luer87p4drdrby67cc6xq8mjo
			unique
);

alter table tour_type owner to postgres;

create table if not exists users
(
	id bigserial not null
		constraint users_pkey
			primary key,
	email varchar(40)
		constraint uk6dotkott2kjsp8vw4d0m25fb7
			unique,
	name varchar(40),
	password varchar(100)
);

alter table users owner to postgres;

create table if not exists tour
(
	id bigserial not null
		constraint tour_pkey
			primary key,
	is_hot boolean,
	name varchar(255),
	price integer,
	user_id bigint
		constraint fkbdpwsmp2lk7bfxe7icley7jcp
			references users,
	route bigint
		constraint fkk4uwkqruu2vvb1lfgdmu86fkb
			references route,
	tour_type_id bigint
		constraint fk1ffg23rlrgmg0pr5frbs2ckhn
			references tour_type
);

alter table tour owner to postgres;

create table if not exists discount
(
	id bigserial not null
		constraint discount_pkey
			primary key,
	amount integer,
	is_percent boolean,
	tour_id bigint
		constraint fkq5hg3jgtok7803s1c9jigiskb
			references tour,
	user_id bigint
		constraint fkf0yuaou3xo6mxbdowl2lfycn7
			references users
);

alter table discount owner to postgres;

create table if not exists user_roles
(
	user_id bigint not null
		constraint fkhfh9dx7w3ubf1co1vdev94g3f
			references users,
	role_id bigint not null
		constraint fkh8ciramu9cc9q3qcqiv4ue8a6
			references roles,
	constraint user_roles_pkey
		primary key (user_id, role_id)
);

alter table user_roles owner to postgres;

