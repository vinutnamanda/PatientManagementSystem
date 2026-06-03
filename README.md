# Patient Management System

## Project Overview

Patient Management System is a web-based application developed using Java Servlets, JDBC, HTML, and MySQL. The application allows users to manage patient records through CRUD (Create, Read, Update, Delete) operations.

## Features

### Add Patient

* Add new patient records to the database.
* Stores patient information such as ID, Name, Age, and Disease.

### Find Patient by ID

* Search for a patient using their unique ID.
* Displays complete patient details.

### Update Patient

* Retrieve existing patient details.
* Modify and update patient information in the database.

### Delete Patient

* Remove patient records from the database using Patient ID.

### Home Page Navigation

* Easy navigation between different functionalities.
* Separate pages for each operation.

## Technologies Used

### Frontend

* HTML

### Backend

* Java Servlets
* JDBC

### Database

* MySQL

### Server

* Apache Tomcat 9

## Database Schema

Table: patient

| Column Name | Data Type         |
| ----------- | ----------------- |
| id          | INT (Primary Key) |
| name        | VARCHAR(50)       |
| age         | INT               |
| disease     | VARCHAR(50)       |

## Project Structure

Patient_Management_System

* home.html
* addPatient.html
* findPatient.html
* AddPatientServlet.java
* FindPatientServlet.java
* UpdatePatientServlet.java
* DeletePatientServlet.java
* MySQL Database (hospital1)

## Functional Flow

1. User opens Home Page.
2. User selects an operation.
3. Servlet processes the request.
4. JDBC communicates with MySQL database.
5. Results are displayed to the user.

## Learning Outcomes

* Understanding Java Servlet lifecycle.
* Working with JDBC and MySQL.
* Handling HTTP GET and POST requests.
* Implementing CRUD operations.
* Dynamic HTML generation using Servlets.
* Web application deployment using Apache Tomcat.

## Future Enhancements

* Display All Patients.
* Login and Authentication.
* Session Management using HttpSession.
* Email Notifications using Jakarta Mail.
* Improved UI using CSS and Bootstrap.
* JSP Integration.

## Author

Vinutna
