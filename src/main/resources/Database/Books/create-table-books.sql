create table Books(
    book_id uuid default gen_random_uuid() primary key,
    reader_id uuid references Readers(reader_id) on delete set null default null,
    book_title varchar(256) not null,
    book_author varchar(256) not null,
    book_description text
);

alter table books add column book_publisher varchar(128) not null default 'no publisher';
alter table books add column book_publication_year int not null default 0;