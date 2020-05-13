--В системе есть таблица cities. с полями id, name.
--Система парсит объявления и записывывать города.
--В коде системы оказался баг. Он записывал дубликаты городов.
--Москва, Москва, СПб, Казань.
--Багу поправили на уровне приложения, но таблица все равно содержит дубликаты.
--Ваша задача написать sql скрипт, который оставит в таблице только уникальные города. Если было три раза написана Москва. то нужно оставить только одну Москву.

create table cities
(
    id serial primary key,
    name varchar(255)
);

insert into cities(name) values ('Москва');
insert into cities(name) values ('Москва');
insert into cities(name) values ('Москва');
insert into cities(name) values ('Москва');

insert into cities(name) values ('Санкт-Петербург');
insert into cities(name) values ('Санкт-Петербург');
insert into cities(name) values ('Санкт-Петербург');

insert into cities(name) values ('Казань');
insert into cities(name) values ('Казань');

insert into cities(name) values ('Уфа');

delete from cities where id not in
                         (select min(c.id) from cities as c group by c.name);