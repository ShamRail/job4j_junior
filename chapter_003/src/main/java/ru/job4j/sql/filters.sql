drop table if exists product;
drop table if exists type;

create table type(
                     id serial primary key,
                     name varchar(255)
);

create table product(
                        id serial primary key,
                        name varchar(255),
                        type_id int references type(id),
                        expired_date date,
                        price float
);

insert into type(name) values('СЫР');
insert into type(name) values('Молочный');
insert into type(name) values('Мясной');
insert into type(name) values('Хлебный');
insert into type(name) values('Рыба');

insert into product(name, type_id, expired_date, price) VALUES ('Пармизан', 1, '2020-06-15', 100);
insert into product(name, type_id, expired_date, price) VALUES ('Крестьянский', 1, '2020-06-01', 50);
insert into product(name, type_id, expired_date, price) VALUES ('Плавленный', 1, '2020-06-15', 30);

insert into product(name, type_id, expired_date, price) VALUES ('Молоко', 2, '2020-05-15', 100);
insert into product(name, type_id, expired_date, price) VALUES ('Творог', 2, '2020-05-22', 75);
insert into product(name, type_id, expired_date, price) VALUES ('Сметана', 2, '2020-05-23', 50);
insert into product(name, type_id, expired_date, price) VALUES ('мороженное 45 копеек', 2, '2020-07-23', 361);

insert into product(name, type_id, expired_date, price) VALUES ('Сосиски', 3, '2020-06-01', 200);
insert into product(name, type_id, expired_date, price) VALUES ('Колбаса', 3, '2020-05-10', 255);
insert into product(name, type_id, expired_date, price) VALUES ('Ветчина', 3, '2020-06-25', 300);

insert into product(name, type_id, expired_date, price) VALUES ('Батон', 4, '2020-05-11', 50);
insert into product(name, type_id, expired_date, price) VALUES ('Багет', 4, '2020-05-12', 60);
insert into product(name, type_id, expired_date, price) VALUES ('Хлеб', 4, '2020-05-13', 40);

insert into product(name, type_id, expired_date, price) VALUES ('Скумбрия', 5, '2020-06-19', 300);
insert into product(name, type_id, expired_date, price) VALUES ('Селедка', 5, '2020-07-20', 220);
insert into product(name, type_id, expired_date, price) VALUES ('Лосось', 5, '2020-08-21', 500);


--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product inner join type t on product.type_id = t.id where t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product as p
where
    current_date < p.expired_date
and
    mod(cast(extract(month from current_date) + 1 as int), 12) = (extract(month from p.expired_date));

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product where price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select * from product inner join type t on product.type_id = t.id where t.name = 'Рыба';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product inner join type t on product.type_id = t.id where t.name = 'СЫР' or t.name = 'Молочный';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(*) as count from product inner join type t on product.type_id = t.id
group by t.name
having count(t.id) < 10;

--8. Вывести все продукты и их тип.
select * from product inner join type t on product.type_id = t.id;