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
