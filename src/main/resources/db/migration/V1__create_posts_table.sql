create TABLE POSTS (
    id integer primary key AUTOINCREMENT,
    title varchar(100) unique NOT NULL,
    content text
)
