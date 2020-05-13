CREATE TABLE company
(
    id serial primary key,
    name character varying
);

CREATE TABLE person
(
    id serial primary key,
    name character varying,
    company_id int REFERENCES company(id)
);

INSERT INTO company(name) VALUES('IBM');
INSERT INTO company(name) VALUES('Microsoft');
INSERT INTO company(name) VALUES('Apple');
INSERT INTO company(name) VALUES('Google');
INSERT INTO company(name) VALUES('Intel');

INSERT INTO person(name, company_id) VALUES('Jack', 1);
INSERT INTO person(name, company_id) VALUES('Mike', 2);
INSERT INTO person(name, company_id) VALUES('Daniel', 3);
INSERT INTO person(name, company_id) VALUES('Georg', 4);
INSERT INTO person(name, company_id) VALUES('Henry', 5);
INSERT INTO person(name, company_id) VALUES('Mitchal', 5);


-- есть две таблицы )
-- нужно
-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person

select p.name, c.name from person as p inner join company as c on c.id = p.company_id where c.id <> 5;

-- 2) Select the name of the company with the maximum number of persons + number of persons in this company

select c.name, count(p.id) from company as c inner join person as p on c.id = p.company_id
group by c.name
order by count(p.id) desc
limit 1;