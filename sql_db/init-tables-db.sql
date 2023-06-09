create table stepathon_user
(
    id       integer generated by default as identity
        primary key,
    password varchar(255),
    score    integer,
    steps    integer,
    username varchar(255)
);

alter table stepathon_user
    owner to postgres;

create table location
(
    id        integer generated by default as identity
        primary key,
    latitude  double precision,
    longitude double precision,
    name      varchar(255),
    active    boolean
);

alter table location
    owner to postgres;



