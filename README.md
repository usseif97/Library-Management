# Spark Intializer  
![Screenshot 2024-04-19 224731](https://github.com/usseif97/Library-Management/assets/47598030/7706a629-a1b5-462b-a21f-05e414619fe7)  

  
# Entities

## 1) Book

Book Entity include the information of the books in the Library.


```java
id: Long - PK - Generated Sequence
title: Text - Unique
isbn: Text - Unique
author: Text
publicationYear: LocalDate
```
**Get all Books**  
![Screenshot 2024-04-19 202811](https://github.com/usseif97/Library-Management/assets/47598030/f43ca891-5d28-46b9-80fc-b098f7e5986b)  

**Get Book by ID**  
![Screenshot 2024-04-19 202832](https://github.com/usseif97/Library-Management/assets/47598030/e8946fa4-f270-432a-a6e2-b10fe91ae3a3)  

**Add Book**  
![Screenshot 2024-04-19 210128](https://github.com/usseif97/Library-Management/assets/47598030/35174bd6-efe1-41de-944c-18c9266585a0)  

**Update Book**  
![Screenshot 2024-04-19 210255](https://github.com/usseif97/Library-Management/assets/47598030/2e872dbf-92e7-4a5a-b096-ddfa6ef3289f)  

**Delete Book**  
![Screenshot 2024-04-19 210309](https://github.com/usseif97/Library-Management/assets/47598030/e3427381-701a-41fc-bd04-0e053db3dd6a)  
  

## 2) Patron

Patron Entity include the information of the patrons.


```java
id: Long - PK - Generated Sequence
email: Text - Unique
name: Text
address: Text
address: Text
dob: LocalDate
age: Integer - Transient
```
**Get all Patrons**  
![Screenshot 2024-04-20 000724](https://github.com/usseif97/Library-Management/assets/47598030/c5940220-6419-41a2-b856-071d5d7df4bb)  


**Get Patron by ID**  
![Screenshot 2024-04-20 000737](https://github.com/usseif97/Library-Management/assets/47598030/7b075702-fdd7-406b-bc1d-48a6b40a8b39)  


**Add Patron**  
![Screenshot 2024-04-20 000855](https://github.com/usseif97/Library-Management/assets/47598030/4c9f0389-76bd-4e86-9588-dfc7089a6e1d)  


**Update Patron**  
![Screenshot 2024-04-20 000940](https://github.com/usseif97/Library-Management/assets/47598030/9ebaa15e-ddd4-48e0-a886-58a5e0eb9f26)  


**Delete Patron**  
![Screenshot 2024-04-20 000959](https://github.com/usseif97/Library-Management/assets/47598030/fe5ad195-4c77-4146-a8ff-8546b85113eb)  

  


## 3) Borrow

Borrow Entity include the information of the borrowed books & the patrons.

```java
id: Long - PK - Generated Sequence
book_id: Long
patron_id: Long
/* book_id & patron_id: Unique */
is_returned: boolean - default=false
return_date: LocalDate - nullable
```
**is_returned,** refer to the borrowed book have returned or not  
**return_date,** refer to the date that the book is supposed to be returned back by the patron  
  
**Add a borrow of a book by a patron**  
![Screenshot 2024-04-19 210443](https://github.com/usseif97/Library-Management/assets/47598030/04cf9f9d-754c-489e-a4c9-db36dc3335c8)  
![Screenshot 2024-04-19 210514](https://github.com/usseif97/Library-Management/assets/47598030/5238c549-cdce-4cbc-a5a4-0511053ad797)  
![Screenshot 2024-04-19 210520](https://github.com/usseif97/Library-Management/assets/47598030/4e43fa9c-cb22-4f94-9ba8-300b672ffc94)  
  
**Record the return of a borrowed book by a patron**  
![Screenshot 2024-04-19 210539](https://github.com/usseif97/Library-Management/assets/47598030/58aefdb3-3fa0-4594-90a5-e5ac0af072f1)  
![Screenshot 2024-04-19 210547](https://github.com/usseif97/Library-Management/assets/47598030/87d86e61-16b5-4b4f-825a-0b025a1e303d)  
  

# Relationship
![Screenshot 2024-04-19 235551](https://github.com/usseif97/Library-Management/assets/47598030/68653deb-3335-4f01-9703-ae05554e0984)  


# Database  
**Postgres database used with the following configuration**
```java
spring.datasource.url=jdbc:postgresql://localhost:5432/library  
spring.datasource.username= postgres  
spring.datasource.password= 2011y  
spring.jpa.hibernate.ddl-auto=update    
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
server.error.include-message=always   
```

# Testing  
**Mockito framework**  
1- BookControllerTest  
2- PatronControllerTest  
3- BorrowControllerTest  
  

# Sample Example
![Screenshot 2024-04-20 045456](https://github.com/usseif97/Library-Management/assets/47598030/df45e1bb-61d8-40aa-ac30-a965b22405eb)  
![Screenshot 2024-04-20 045509](https://github.com/usseif97/Library-Management/assets/47598030/6d13a769-4456-450a-b7bd-37fa51815b63)  
![Screenshot 2024-04-20 045529](https://github.com/usseif97/Library-Management/assets/47598030/bb4d31d5-d838-4c3f-a633-c4fa5b98c5e8)  
![Screenshot 2024-04-20 045543](https://github.com/usseif97/Library-Management/assets/47598030/7167c28a-da34-43f7-812b-db986dac465f)  


# Project Architecture  
![Screenshot 2024-04-20 051743](https://github.com/usseif97/Library-Management/assets/47598030/7bd9acfd-3e53-467a-bc77-c798f958f0c6)  

  



