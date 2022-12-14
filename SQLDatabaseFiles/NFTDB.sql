drop database NFTDB;
create database NFTDB;
use NFTDB;

drop table if exists User;
create table if not exists User (
 email varchar(100) not null,
 userPass varchar(100) not null,
 firstName varchar(50) not null,
 lastName varchar(50) not null,
 birthday DATE not null,
 address VARCHAR(60) , 
 address_city VARCHAR(20), 
 address_state VARCHAR(2),
 address_zip_code VARCHAR(5),
  balance decimal(65,2) not null default 100,
 primary key(email)
 );
 
 
 drop table if exists NFT;
 create table if not exists NFT (
 NFTID int(6) not null auto_increment,
 nftName VARCHAR (250) NOT NULL,
 nftDescription VARCHAR (500) NOT NULL,
 imageURL varchar(500) not null,
 nftOwner varchar(100) not null,
 createdBy varchar(100) not null,
 dateCreated date not null,
 primary key(NFTID),
 Unique(nftName),
 foreign key(nftOwner) references User(email),
 foreign key(createdBy) references User(email)
 );
 
 drop table if exists Sale_Listings;
 create table if not exists Sale_Listings (
 listID int(6) not null auto_increment,
 nftListed int(6) not null,
 nftSeller varchar(100) not null,
 price decimal(65,2) not null,
 datePosted date not null,
 endingDate date not null,
 nftSold tinyint,
 primary key(listID),
 foreign key(nftListed) references NFT(NFTID),
 foreign key(nftSeller) references User(email)
 );
 
 drop table if exists Sale_Oders;
 create table if not exists Sale_Orders (
 saleID int(6) not null auto_increment,
 sale_Listing int(6) not null,
 nftToBeSold int(6) not null,
 nftSeller varchar(100) not null,
 soldTo varchar(100),
 dateSold date,
 primary key(saleID),
 foreign key(soldTo) references User(email),
 foreign key(nftToBeSold) references NFT(NFTID),
 foreign key(nftSeller) references User(email),
 foreign key(sale_Listing) references Sale_Listings(listID)
 );
 
 
 drop table if exists Transfer_Orders;
 create table if not exists Transfer_Orders(
 transferID int(6) not null auto_increment,
 nftToBeTransferred int(6) not null,
 nftOwner varchar(100) not null,
 transferredTo varchar(100) not null,
 dateTransferred date not null,
 primary key(transferID),
 foreign key(transferredTo) references User(email),
 foreign key(nftToBeTransferred) references NFT(NFTID),
 foreign key(nftOwner) references User(email)
 );
 
drop view if exists mostCreated; 
create view mostCreated(createdBy, Num)
as select createdBy, count(*) as Num
from NFT 
group by createdBy;

drop view if exists bigSeller; 
create view bigSeller(nftSeller, Num)
as select nftSeller, count(*) as Num
from sale_orders
group by nftSeller;

drop view if exists bigBuyer; 
create view bigBuyer(soldTo, Num)
as select soldTo, count(*) as Num
from sale_orders
group by soldTo;

drop view if exists getHotNft; 
create view getHotNft
as select n.nftName, 
(select count(1) from sale_orders s where s.nftToBeSold=n.NFTID) 
+
(select count(1) from transfer_orders t where t.nftToBeTransferred=n.NFTID) 
as num
from NFT n 
group by n.NFTID;

drop view if exists goodBuyers; 
create view goodBuyers
as select soldTo, count(distinct nftToBeSold) as nftSold
from sale_orders
group by soldTo
having nftSold >= 3;

drop view if exists innactiveUsers; 
create view innactiveUsers
as select u.email
from user u
left join NFT n
on n.createdBy=u.email
left join sale_orders s
on s.nftSeller=u.email or s.soldTo=u.email
left join transfer_orders t
on t.nftOwner=u.email or t.transferredTo=u.email
where 
u.email is not null and
n.createdBy is null and
s.nftSeller is null and
s.soldTo is null and
t.nftOwner is null and
t.transferredTo is null
group by u.email;

drop view if exists userStatistics; 
create view userStatistics
as select u.email, 
(select count(1) from sale_orders s where s.soldTo=u.email) as buys,
(select count(1) from sale_orders s where s.nftSeller=u.email) as sells,
(select count(1) from transfer_orders t where t.nftOwner=u.email) as transfers,
(select count(1) from NFT n where n.nftOwner=u.email) as nftsOwned
from User u
group by u.email;

drop view if exists DiamondHands; 
create view DiamondHands
as select u.email, 
(select count(1) from sale_orders s where s.soldTo=u.email and u.email
not in(select s.nftSeller from sale_orders s where s.nftSeller=u.email)) as count
from user u
group by u.email
having count > 0;

drop view if exists PaperHands; 
create view PaperHands
as select u.email
from user u 
where (select count(1) from sale_orders s where s.soldTo=u.email and u.email
in(select s.nftSeller from sale_orders s where s.nftSeller=u.email) and u.email
not in(select nftOwner from NFT where nftOwner=u.email))
group by u.email;


 show tables;

