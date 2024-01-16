# Employee manager

Java object-oriented application implements simplified model of human resources management. Allows user to manage entities of employees and assign them into specified projects commissioned by clients.

## Table of Contents

1. [Features](#features)
2. [Technology Stack](#technology-stack)
3. [Design Patterns](#design-patterns)
4. [Installation](#installation)
5. [Author](#author)

## Features

- **CRUD Operations**: The application follows the CRUD (Create, Read, Update, Delete) model for managing employees, clients, projects, and employee-project assignments.
- **Employee Management**: Easily add, view, update, and delete employee records. Each employee profile includes relevant information such as name, contact details, position etc.
- **Client Management**: Manage client information. Keep track of client details including name and contact information.
- **Project Management**: Supervise projects. Track project details such as name, status and its principal.
- **Assignment Management**: Assign employees to projects.

## Technology Stack

- **Java**: Utilized for the application backend logic.
- **Swing**: Used for creating a user-friendly graphical user interface.
- **JDBC**: Facilitates interaction with the PostgreSQL database.
- **PostgreSQL**: Chosen as the database management system for storing application data.

## Design Patterns

- **Model-View-Controller**: main pattern defining the whole application architecture.
- **Facade**: implemented in `Service` package classes.
- **Singleton**: implemented in `Database.java` and `Login.java`.

## Installation

To run the application locally, follow these steps:

1. **Clone the Repository**:

    ```
   git clone https://github.com/jakub-gorzkowski/Employee-Manager
   ```

2. **Set Up PostgreSQL Database**:
- Install PostgreSQL if you haven't already.
- Create a new database with `schema.sql` and configure the connection details in `Database.java`.

3. **Compile and Run the Application**:
- Compile the Java files.
- Run the application's main file to launch the user interface.

## Author
Jakub Gorzkowski