--1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).

-- Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах.
-- У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.

create table meeting
(
    id serial primary key,
    name varchar(255)
);

create table muser
(
    id serial primary key,
    name varchar(255)
);

create table mstatus
(
    id serial primary key,
    name varchar(255)
);

create table meeting_user
(
    id serial primary key,
    meeting_id int references meeting(id),
    user_id int references muser(id),
    status_id int references mstatus(id)
);

insert into mstatus(name) values ('Ожидает ответа');
insert into mstatus(name) values ('Принято');
insert into mstatus(name) values ('Отклонено');

insert into meeting(name) values ('Конференция по Java');
insert into meeting(name) values ('Конференция по JavaScript');
insert into meeting(name) values ('Конференция по SQL');
insert into meeting(name) values ('Конференция по микроконтроллерам');
insert into meeting(name) values ('Конференция по MS Office');

insert into muser(name) values ('Иван');
insert into muser(name) values ('Николай');
insert into muser(name) values ('Петр');
insert into muser(name) values ('Василий');
insert into muser(name) values ('Андрей');
insert into muser(name) values ('Георгий');

insert into meeting_user(meeting_id, user_id, status_id) values (1, 1, 2);
insert into meeting_user(meeting_id, user_id, status_id) values (2, 1, 3);
insert into meeting_user(meeting_id, user_id, status_id) values (3, 1, 1);

insert into meeting_user(meeting_id, user_id, status_id) values (1, 2, 2);
insert into meeting_user(meeting_id, user_id, status_id) values (2, 2, 2);
insert into meeting_user(meeting_id, user_id, status_id) values (3, 2, 2);

insert into meeting_user(meeting_id, user_id, status_id) values (1, 3, 1);
insert into meeting_user(meeting_id, user_id, status_id) values (2, 3, 2);
insert into meeting_user(meeting_id, user_id, status_id) values (3, 3, 3);

insert into meeting_user(meeting_id, user_id, status_id) values (1, 4, 2);
insert into meeting_user(meeting_id, user_id, status_id) values (2, 5, 1);
insert into meeting_user(meeting_id, user_id, status_id) values (3, 6, 3);


--2. Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.

select m.name, count(s.name) from meeting_user as mu
    left join meeting as m on mu.meeting_id = m.id
    inner join mstatus as s on mu.status_id = s.id
where s.name = 'Принято'
group by m.name;

--3. Нужно получить все совещания, где не было ни одной заявки на посещения

select m.name from meeting as m
    left join meeting_user mu on m.id = mu.meeting_id
where mu.status_id is null;