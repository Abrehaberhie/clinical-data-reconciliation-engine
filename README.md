Clinical Data Reconciliation Engine (Mini Version) Overview

This project implements a Clinical Data Reconciliation Engine designed to detect and analyze conflicting patient information across multiple healthcare systems.

Healthcare providers often store patient data in different systems such as hospital Electronic Health Records (EHR), clinics, and pharmacies. These systems may contain inconsistent or outdated information. This service identifies data conflicts, validates clinical data quality, and reconciles medication records from multiple sources.

The application exposes REST APIs that allow healthcare systems to:

Detect conflicting patient records

Validate clinical data quality

Reconcile medication records from multiple sources

Technology Stack

Java 22

Spring Boot

Spring Web MVC

Spring Data JPA

H2 In-Memory Database

OpenAPI / Swagger

Maven

Project Architecture

The application follows a layered architecture to maintain separation of concerns.

Controller Layer ↓ Service Layer ↓ Repository Layer ↓ H2 Database Controller Layer

Handles incoming HTTP requests and exposes REST endpoints.

Service Layer

Contains the business logic including:

conflict detection

medication reconciliation

clinical reasoning

data quality validation

Repository Layer

Uses Spring Data JPA to interact with the database.

Running the Application Prerequisites

Make sure the following are installed:

Java 22

Maven

Git

Clone the Repository git clone https://github.com/Abrehaberhie/ehr-reconciliation-engine.git cd ehr-reconciliation-engine Run the Application mvn spring-boot:run

The application will start at:

http://localhost:8080 API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

OpenAPI JSON documentation:

http://localhost:8080/v3/api-docs

Swagger allows you to test all API endpoints directly from the browser.

API Endpoints

Patient Record Reconciliation
Endpoint

POST /api/reconciliation

Identifies conflicting patient data from different sources.

Example Request { "patientId": "P1001", "records": [ { "source": "Hospital", "fieldName": "bloodType", "fieldValue": "O+" }, { "source": "Clinic", "fieldName": "bloodType", "fieldValue": "A+" } ] } Example Response { "patientId": "P1001", "conflicts": [ { "fieldName": "bloodType", "source": "Hospital", "fieldValue": "O+", "conflict": true }, { "fieldName": "bloodType", "source": "Clinic", "fieldValue": "A+", "conflict": true } ] }

Medication Reconciliation
Endpoint

POST /api/reconcile/medication

Analyzes medication records from multiple systems and determines the most reliable medication entry.

Example Request { "sources": [ { "system": "Pharmacy", "medication": "Aspirin 81mg daily", "lastUpdated": "2026-03-10", "lastFilled": "2026-03-12", "sourceReliability": "high" }, { "system": "Hospital EHR", "medication": "Aspirin 325mg daily", "lastUpdated": "2026-02-20", "sourceReliability": "medium" } ] }

Data Quality Validation
Endpoint

POST /api/validate/data-quality

Validates the completeness and quality of clinical data.

Example Request { "demographics": { "firstName": "John", "lastName": "Doe", "dob": "1990-01-01" }, "medications": [ "Aspirin 81mg daily" ], "allergies": [ "Peanuts" ], "conditions": [ "Hypertension" ], "vitalSigns": { "bp": "120/80", "hr": "72" }, "lastUpdated": [ "2026-03-14" ] } Database

The application uses an H2 in-memory database for development and testing.

H2 Console:

http://localhost:8080/h2-console

Database connection information appears in the application logs when the application starts.

Example:

jdbc:h2:mem:database-name

Username:

sa

Password:

(empty) Design Decisions

Some important design considerations used in this project:

Layered architecture for maintainability

DTO-based API requests and responses

Spring Data JPA for database persistence

H2 database for lightweight development

Swagger/OpenAPI for interactive API documentation

Future Improvements

Possible enhancements include:

Adding authentication and authorization

Advanced clinical rule-based reconciliation

Integration with external EHR systems

Automated testing

Containerization using Docker

Author

Abreha Gebreslasie Computer Science Student University of the District of Columbia

GitHub:

https://github.com/Abrehaberhie
