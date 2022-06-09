
create database furnitureShop
use furnitureShop


create table AdminInfo(
AdminId int identity(1,1) not null primary key,
Username varchar(50) not null,
Password varchar(50) not null
)

Insert Into AdminInfo(Username,Password)
values('abir','12345')

select * from AdminInfo

--FIRST TABLE....
CREATE TABLE CUSTOMER
(
CustomerId int IDENTITY(101,1) PRIMARY KEY,
FirstName varchar(50) NOT NULL,
LastName varchar(50) NOT NULL,
Phone varchar(11) not NULL unique,
Address varchar(200) NULL,
)

--drop table CUSTOMER
select *from customer
INSERT INTO CUSTOMER(FirstName,LastName,Phone,Address)
VALUES('Latifur','Rahman','01521246401','Kurigram'),
      ('Abir','Hossain','01621256302','Chandpur'),
      ('Radit','Chowdhury','01721246598','Dhaka')




--SECOND TABLE..
CREATE TABLE ORDERR
(
OrderId int IDENTITY(201,1) PRIMARY KEY,
CustomerId int NOT NULL FOREIGN KEY REFERENCES CUSTOMER (CustomerId),
OrderDate date NULL,
DeleveryDate date NULL,
)

--drop table ORDERR
SELECT *FROM ORDERR
insert into ORDERR(CustomerId,OrderDate,DeleveryDate)
values('101','2020-05-29','2020-06-05'),
      ('102','2020-05-30','2020-06-06'),
	  ('103','2020-05-31','2020-06-07')





--THIRD TABLE..
CREATE TABLE WORKER
(
WorkerId int IDENTITY(1001,1) PRIMARY KEY,
FirstName varchar(50) NOT NULL,
LastName varchar(50) NOT NULL,
Phone varchar(11) NULL,
Address varchar(200) NULL,
)

--drop table WORKER
select *from WORKER
INSERT INTO WORKER(FirstName,LastName,Phone,Address)
VALUES('Nur','Alam','01356246441','Ulipur'),
      ('Nur','Alam','01636266502','Khulna'),
      ('Mostak','Ahmed','01956246596','Rangpur')






--FOURTH TABLE..
CREATE TABLE WOOD
(
WoodId int IDENTITY(2001,1) PRIMARY KEY,
Name varchar(50) NOT NULL,
PricePerUnit money NULL,
Quantity int NULL,
)
--drop table WOOD
select *from WOOD
INSERT INTO WOOD(Name,PricePerUnit,Quantity)
VALUES('Neem','1500','10'),
      ('Sal','2500','11'),
      ('Cork','3000','12')





--5th TABLE..
CREATE TABLE WORKASSIGN
(
WorkId int IDENTITY(3001,1) PRIMARY KEY,
WorkerId int NOT NULL FOREIGN KEY REFERENCES WORKER (WorkerId),
OrderId int NOT NULL FOREIGN KEY REFERENCES ORDERR (OrderId),
)
--drop table WORKASSIGN
select *from WORKASSIGN
INSERT INTO WORKASSIGN(WorkerId,OrderId)
VALUES('1001','201'),
      ('1002','202'),
      ('1003','203')




--6th TABLE..
CREATE TABLE FURNITURE
(
FurnitureId int IDENTITY(4001,1) PRIMARY KEY,
Name varchar(50) NOT NULL,
MakingCost money NULL,
)
select *from FURNITURE
INSERT INTO FURNITURE(Name,MakingCost)
VALUES('Chair','4000'),
      ('Table','10000'),
      ('Cabinet','25000')

--drop table FURNITURE



--7th TABLE..

CREATE TABLE MESUREMENT
(
MesurementId int IDENTITY(5001,1) PRIMARY KEY,
Height numeric(8,2)  NULL,
Width numeric(8,2)  NULL,
Length numeric(8,2)  NULL,
WoodQuantity int NULL,
ExtraFeature text NULL,
)
--drop table MESUREMENT
select *from MESUREMENT
INSERT INTO MESUREMENT(Height,Width,Length,WoodQuantity,ExtraFeature)
VALUES('13','5.5','20','7','Color red'),
      ('14','6.5','22','10','There will be one side glass'),
	  ('15','7.5','25','15','There will be 5 drawers')





--8th TABLE..

CREATE TABLE ITEM
(
ItemId int IDENTITY(6001,1) PRIMARY KEY,
OrderId int NOT NULL FOREIGN KEY REFERENCES ORDERR (OrderId),
FurnitureId int NOT NULL FOREIGN KEY REFERENCES FURNITURE (FurnitureId),
WoodId int NOT NULL FOREIGN KEY REFERENCES WOOD (WoodId),
MesurementId int NOT NULL FOREIGN KEY REFERENCES MESUREMENT (MesurementId),
ItemQuantity int not null, 
Cost money not null,
 )

--drop table ITEM

select * from ITEM
INSERT INTO ITEM(OrderId,FurnitureId,WoodId,MesurementId,ItemQuantity,Cost )
VALUES('203','4001','2001','5001','2','1250'),
      ('201','4002','2002','5002','1','2250'),
	  ('202','4003','2003','5003','2','2250')



