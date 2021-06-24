create database main;

create table currency(
id bigint(18) AUTO_INCREMENT PRIMARY KEY,
english_name VARCHAR(50) NOT NULL,
chinese_name VARCHAR(50) NOT NULL
);

create index index_english_name on currency(english_name);

insert into currency (english_name, chinese_name) values ('USD', '美金'), ('GBP', '英鎊'), ('EUR', '歐元');