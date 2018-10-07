-- Create table
create table USER_ACCOUNT
(
USER_NAME VARCHAR(30) not null,
GENDER    VARCHAR(1) not null,
PASSWORD  VARCHAR(30) not null,
primary key (USER_NAME)
);
 
-- Create table
create table PRODUCT
(
CODE  VARCHAR(20) not null,
NAME  VARCHAR(128) not null,
PRICE FLOAT not null,
primary key (CODE)
) ;
 
-- Insert data: ---------------------------------------------------------------
 
insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('yu', 'M', 'yu123');
 
insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('kim', 'M', 'kim123');
 
insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('lee', 'M', 'lee123');

insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('park', 'F', 'park123');

insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('jung', 'F', 'jung123');

insert into product (CODE, NAME, PRICE)
values ('P001', 'Java Programming', 15000);

insert into product (CODE, NAME, PRICE)
values ('P002', 'C Programming', 10000);

insert into product (CODE, NAME, PRICE)
values ('P003', 'C++ Programming', 12000);

insert into product (CODE, NAME, PRICE)
values ('P004', 'C# Programming', 13000);
 
insert into product (CODE, NAME, PRICE)
values ('P005', 'Python Programming', 11000);
