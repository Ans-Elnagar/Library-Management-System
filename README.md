# Library Management System  
a Library Management System API using Spring Boot. The system should allow librarians
to manage books, patrons, and borrowing records.
### Requirements
* MySQL database with root pass=root or Docker to run a DB container.  
  You can set the password to whatever you want but update the application.properties file.
* Optionally you can use Postman I have already added a suite file of requests to test the API.  
   The file name is library.postman_collection.json. You can import it in Postman.
## How to run

1. First start the DB or Docker container using the following command
     ```bash
     docker run --name mysql-library -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=library -p 3306:3306 -d mysql:8.0
     ```
2. Start the project using the following command
     ```bash
     mvn spring-boot:run
     ```
3. You can test the API using Postman or any other tool.
