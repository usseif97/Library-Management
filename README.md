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


# Relationship
![Screenshot 2024-04-19 235551](https://github.com/usseif97/Library-Management/assets/47598030/68653deb-3335-4f01-9703-ae05554e0984)
