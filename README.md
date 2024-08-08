Documentation for Library Management System
1. Running the Application
To run the Library Management System, follow these steps:

Prerequisites:

Java 17 or later: Ensure you have JDK 17 or later installed.
Maven: Use Apache Maven for building the application.
Steps to Run:

Clone the Repository:

bash
Copy code
git clone https://github.com/your-repo/library-management-system.git
cd library-management-system
Build the Application:

bash
Copy code
mvn clean install
Run the Application:

bash
Copy code
mvn spring-boot:run
Alternatively, you can run the application directly from your IDE by running the main method in the LibraryManagementSystemApplication class.

Access the Application:

The application will start on http://localhost:8080 by default. You can access the API endpoints via this base URL.

2. Interacting with API Endpoints
The application provides several API endpoints to manage books, patrons, and borrowing records. Here’s a summary of the available endpoints:

Base URL: http://localhost:8080

Books
Get All Books:

http
Copy code
GET /api/books
Get Book by ID:

http
Copy code
GET /api/books/{id}
Path Parameter:
id - The ID of the book.
Add a New Book:

http
Copy code
POST /api/books
Request Body:
json
Copy code
{
  "title": "Book Title",
  "author": "Author Name",
  "publicationYear": 2024,
  "isbn": "1234567890",
  "available": true
}
Update Book Details:

http
Copy code
PUT /api/books/{id}
Path Parameter:

id - The ID of the book.
Request Body:

json
Copy code
{
  "title": "Updated Title",
  "author": "Updated Author",
  "publicationYear": 2024,
  "isbn": "0987654321",
  "available": false
}
Delete a Book:

http
Copy code
DELETE /api/books/{id}
Path Parameter:
id - The ID of the book to delete.
Patrons
Get All Patrons:

http
Copy code
GET /api/patrons
Get Patron by ID:

http
Copy code
GET /api/patrons/{id}
Path Parameter:
id - The ID of the patron.
Add a New Patron:

http
Copy code
POST /api/patrons
Request Body:
json
Copy code
{
  "name": "Patron Name",
  "email": "patron@example.com",
  "phoneNumber": "1234567890",
  "password": "password123"
}
Update Patron Details:

http
Copy code
PUT /api/patrons/{id}
Path Parameter:

id - The ID of the patron.
Request Body:

json
Copy code
{
  "name": "Updated Name",
  "email": "updated@example.com",
  "phoneNumber": "0987654321"
}
Delete a Patron:

http
Copy code
DELETE /api/patrons/{id}
Path Parameter:
id - The ID of the patron to delete.
Borrowing Records
Get All Borrowing Records:

http
Copy code
GET /api/borrowings
Get Borrowing Record by ID:

http
Copy code
GET /api/borrowings/{id}
Path Parameter:
id - The ID of the borrowing record.
Add a New Borrowing Record:

http
Copy code
POST /api/borrowings
Request Body:
json
Copy code
{
  "patronId": 1,
  "bookId": 1,
  "borrowDate": "2024-08-01",
  "returnDate": "2024-08-15"
}
Update Borrowing Record Details:

http
Copy code
PUT /api/borrowings/{id}
Path Parameter:

id - The ID of the borrowing record.
Request Body:

json
Copy code
{
  "patronId": 1,
  "bookId": 1,
  "borrowDate": "2024-08-01",
  "returnDate": "2024-08-20"
}
Delete a Borrowing Record:

http
Copy code
DELETE /api/borrowings/{id}
Path Parameter:
id - The ID of the borrowing record to delete.
3. Authentication
If JWT-based authentication is implemented:

Obtain a JWT Token:

http
Copy code
POST /api/authenticate
Request Body:

json
Copy code
{
  "username": "your-username",
  "password": "your-password"
}
Response:

json
Copy code
{
  "token": "your-jwt-token"
}
Use JWT Token to Access Protected Endpoints:

Include the Authorization header in your requests:

http
Copy code
Authorization: Bearer your-jwt-token
Example for getting all books:

http
Copy code
GET /api/books
Authorization: Bearer your-jwt-token
4. Running Unit Tests
To ensure your application behaves as expected:

Run Unit Tests:

Using Maven:

bash
Copy code
mvn test
Using Gradle:

bash
Copy code
gradle test
Verify Test Results:
Ensure all tests pass and review any failures to fix issues.

5. Further Documentation
For additional information, you may need to document:

Error Handling: Describe the format and types of error responses.
Data Models: Include details about data models and their fields.
Integration with Other Services: Provide details on how to integrate with external services or APIs if applicable.
