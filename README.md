# Library Management System

## Overview

Library Management System is a Spring Boot REST API project that manages books in a library. The application provides complete CRUD operations along with Book Issue and Book Return functionality using Spring Transaction Management (`@Transactional`) to ensure ACID properties.

This project demonstrates how transactions can be used to maintain data consistency and integrity while performing critical operations such as issuing and returning books.

---

## Features

* Add New Books
* View All Books
* View Book By ID
* Update Book Details
* Delete Book
* Issue Books
* Return Books
* Transaction Management using `@Transactional`
* ACID Property Demonstration
* RESTful APIs
* Spring Data JPA Integration
* MySQL Database Support

---

## Technologies Used

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Postman

---

## Entity Structure

### Library

| Field          | Type    | Description            |
| -------------- | ------- | ---------------------- |
| id             | Long    | Primary Key            |
| bookName       | String  | Name of the Book       |
| totalBooks     | Integer | Total Copies Available |
| availableBooks | Integer | Available Copies       |

---

## API Endpoints

### 1. Add Book

**Endpoint**

```http
POST :
http://localhost:8080/library
```

**Request Body**

```json
{
  "bookName": "Java ",
  "totalBooks": 10,
  "availableBooks": 10
}
```

**Response**

```json
{
  "id": 1,
  "bookName": "Java ",
  "totalBooks": 10,
  "availableBooks": 10
}
```

---

### 2. Get All Books

**Endpoint**

```http
GET :
http://localhost:8080/library
```

**Response**

```json
[
    {
        "id": 1,
        "bookName": "Java ",
        "totalBooks": 10,
        "availableBooks": 10
    },
    {
        "id": 2,
        "bookName": "Python ",
        "totalBooks": 10,
        "availableBooks": 10
    },
    {
        "id": 3,
        "bookName": "C ",
        "totalBooks": 10,
        "availableBooks": 10
    }
]
```

---

### 3. Get Book By ID

**Endpoint**

```http
GET :
http://localhost:8080/library/1
```


**Response**

```json
{
  "id": 1,
  "bookName": "Java ",
  "totalBooks": 10,
  "availableBooks": 10
}
```

---

### 4. Update Book

**Endpoint**

```http
PUT :
http://localhost:8080/library/2
```

**Example**


**Request Body**

```json
{
  "bookName": "Advanced Java",
  "totalBooks": 20,
  "availableBooks": 18
}
```

**Response**

```json
{
  "id": 1,
  "bookName": "Advanced Java",
  "totalBooks": 20,
  "availableBooks": 18
}
```

---

### 5. Delete Book

**Endpoint**

```http
DELETE :
http://localhost:8080/library/3
```


**Response**

```text
Book Deleted Successfully
```

---

## Transactional APIs

These APIs use Spring's `@Transactional` annotation to ensure ACID properties.

---

### 6. Issue Book

Issues one or more copies of a book from the library inventory.

**Endpoint**

```http
POST :
http://localhost:8080/library/issue
```

**Request Body**

```json
{
  "bookId": 1,
  "quantity": 2
}
```

**Process**

* Fetch Book Record
* Check Available Quantity
* Reduce Available Copies
* Save Changes
* Commit Transaction

**Response**

```text
Book Issued Successfully
```

**Validation**

* Available books must be greater than or equal to requested quantity.
* Transaction rolls back if validation fails.

---

### 7. Return Book

Returns one or more copies of a book to the library inventory.

**Endpoint**

```http
POST :
http://localhost:8080/library/return
```

**Request Body**

```json
{
  "bookId": 1,
  "quantity": 1
}
```

**Process**

* Fetch Book Record
* Increase Available Copies
* Validate Total Count
* Save Changes
* Commit Transaction

**Response**

```text
Book Returned Successfully
```

**Validation**

* Available books after return cannot exceed total books.
* Transaction rolls back if validation fails.

---

## ACID Properties Demonstration

### Atomicity

A transaction either completes fully or fails completely.

Example:

If a book issue operation fails due to insufficient stock, no database changes are saved.

---

### Consistency

Database rules remain valid before and after every transaction.

Example:

Available books can never become negative.

---

### Isolation

Multiple users can issue or return books simultaneously without corrupting data.

Example:

Concurrent requests do not overwrite each other's changes.

---

### Durability

Once a transaction is committed, the changes remain permanently stored in the database.

---

## Sample Workflow

### Initial State

```text
Java 
Total Books = 10
Available Books = 10
```

### Issue 2 Books

```text
Available Books = 8
```

### Return 1 Book

```text
Available Books = 9
```



## Author

Priyank Mehta

Java Developer | Spring Boot Developer | Backend Developer
