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
 imageURL varchar(250) not null,
 nftOwner varchar(100) not null,
 primary key(NFTID, nftName),
 foreign key(nftOwner) references User(email)
 );
 
 drop table if exists Sale_Listings;
 create table if not exists Sale_Listings (
 listID int(6) not null auto_increment,
 nftListed int(6) not null,
 nftSeller varchar(100) not null,
 price decimal(65,2) not null,
 datePosted date not null,
 endingDate date not null,
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
 
 show tables;

