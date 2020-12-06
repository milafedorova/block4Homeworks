create table authors (id bigserial primary key, title varchar(255));
insert into authors (title)
values
('Pushkin'),
('Tolstoy');

create table books (id bigserial primary key, title varchar(255), price int, author_id bigint references authors (id));
insert into books (title, price, author_id)
values
('Kapitanskaya dochka', 95, 1),
('Pikovaya Dama', 25, 1),
('Voyna i mir', 360, 2),
('Detstvo', 100, 2),
('Anna Korenina', 120, 2);

