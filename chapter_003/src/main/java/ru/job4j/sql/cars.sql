create table if not exists transmission(
    id serial primary key,
    name varchar(255)
);

create table if not exists body(
    id serial primary key,
    name varchar(255)
);

create table if not exists engine(
    id serial primary key,
    name varchar(255)
);

create table if not exists car(
    id serial primary key,
    name varchar(255),
    transmission_id int references transmission(id),
    body_id int references body(id),
    engine_id int references engine(id)
);

insert into transmission(name) values ('Автомат');
insert into transmission(name) values ('Ручное');
insert into transmission(name) values ('Автомат и ручное');
insert into transmission(name) values ('Универсальная');

insert into body(name) values ('Седан');
insert into body(name) values ('Хэчбек');
insert into body(name) values ('Кабриолет');
insert into body(name) values ('Пикап');
insert into body(name) values ('Лимузин');

insert into engine(name) values ('Дизельный');
insert into engine(name) values ('Бензиновый');
insert into engine(name) values ('Газовый');
insert into engine(name) values ('Бензиновый|Газовый');
insert into engine(name) values ('Универсальный');


insert into car(name, transmission_id, body_id, engine_id) values ('М1', 1, 1, 1);
insert into car(name, transmission_id, body_id, engine_id) values ('М2', 2, 2, 2);
insert into car(name, transmission_id, body_id, engine_id) values ('М3', 3, 3, 3);
insert into car(name, transmission_id, body_id, engine_id) values ('М4', 1, 4, 4);

insert into car(name, transmission_id, body_id) values ('М5', 1, 4);
insert into car(name, transmission_id) values ('М6', 1);
insert into car(name) values ('М7');

--1. Вывести список всех машин и все привязанные к ним детали.

select * from car
left join body b on car.body_id = b.id
left join engine e on car.engine_id = e.id
left join transmission t on car.transmission_id = t.id;

--2. Вывести отдельно детали, которые не используются в (ни в одной машине) машине, кузова, двигатели, коробки передач.

select * from transmission as t left join car c on t.id = c.transmission_id
where c.id is null;

select * from body as b left join car c on b.id = c.body_id
where c.id is null;

select * from engine as e left join car c on e.id = c.engine_id
where c.id is null;