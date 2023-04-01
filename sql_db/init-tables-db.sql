create table stepathon_user
(
    id       serial
        constraint uporabnik_pk
            primary key,
    username varchar not null,
    password varchar not null,
    score    integer,
    steps    integer
);

alter table stepathon_user
    owner to postgres;

create unique index uporabnik_id_uindex
    on stepathon_user (id);



create table location
(
    id                serial
        constraint location_pk
            primary key,
    name              varchar not null,
    latitude          numeric,
    longitude         numeric,
    stepathon_user_id integer
);

alter table location
    owner to postgres;

create unique index location_id_uindex
    on location (id);


