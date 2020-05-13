CREATE TABLE girl (
                      id serial PRIMARY KEY,
                      name VARCHAR
);

CREATE TABLE toy (
                     id serial PRIMARY KEY,
                     name VARCHAR
);

--1. Написать sql инструкцию. Инструкция должна добавить колонку toy_id в таблицу girl.

alter table girl add toy_id int references toy(id);

--2. Написать sql запрос на получение имени девочки и ее игрушки.

select g.name, t.name from girl as g inner join toy as t on g.toy_id = t.id;

--3. Написать sql запрос на получение игрушек не привязанных к девочкам.

select * from toy as t left join girl as g on t.id = g.toy_id where g.id is null;