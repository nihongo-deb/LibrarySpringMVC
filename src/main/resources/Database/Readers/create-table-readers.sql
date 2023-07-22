create table Readers(
    reader_id uuid default gen_random_uuid() primary key,
    reader_fio varchar(256) not null,
    reader_email varchar(256) unique not null,
    reader_phone varchar(16) unique not null,
    reader_birthday date not null
);