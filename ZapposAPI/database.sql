create database test ;

use test;

create table restaurant( 
id int not null auto_increment, 
name varchar(40) , 
rating int , 
avg_price float ,
online_delivery boolean, 
number varchar(40) , 
primary key (id)) ;


create table menus ( 
id int not null auto_increment, 
name varchar(20) ,
restid int ,	
primary key (id),
foreign key (restid) references restaurant(id) );
 
 create table mitem( 
 id int not null auto_increment, 
 name varchar(20) , 
 price float(8,2), 
 menuId int , 
 primary key(id),
 foreign key (menuId) references menus(id));
 
 insert into restaurant values
(1 , 'Tamasha' , 5 , 50, true , '217-904-3445'),
(2 , 'Chipotle' , 3 , 15, true , '520-218-8980'),
(5 , 'Sinatra' , 5 , 45, false , '220-518-7880'),
(6 , 'Panda Express' , 3 , 14, true , '120-778-9890'),
(7 , 'Project Pie' , 4 , 55, false , '430-678-6789'),
(8 , 'McDonalds' , 3 , 15, true , '230-333-9990')
;
 
 insert into menus values
(1 , 'Lunch' , 1),(2 , 'Drinks', 1),(3 , 'Lunch' , 2),(4 , 'Dinner' , 2),(5 , 'Lunch' , 5),(6 , 'Dinner' , 5),(7 , 'Late Night' , 5),
(8 , 'Drinks' , 6),
(9 , 'Dinner' , 6),(10 , 'Brunch' , 6),(11 , 'Dinner' , 7);

insert into mitem values
(1 , 'Rice' , 21 , 1),(2 , 'Curry' , 10, 1),(3 , 'Sangria' , 11 , 2),(4 , 'Mimosa' , 10 , 2),(5 , 'Combo' , 15 , 3),
(6 , 'Rice' , 21 , 3),(7 , 'Taco' , 21 , 4),(8 , 'Pizza' , 17 , 5),(9 , 'Pasta' , 21 , 6),(10 , 'Sushi' , 11 , 7),
(11 , 'Red Wine' , 31 , 8),(12 , 'Noodles' , 10 , 9),(13 , 'Chicken' , 11 , 9),(14 , 'Combo' , 21 , 10),(15 , 'Malta' , 12 , 11);
 
