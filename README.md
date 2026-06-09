Chocolate Shop Management System

Overview: 
Chocolate Shop Management System is a desktop client-server application developed as a university software engineering project at the Faculty of Organizational Sciences.
The application enables management of chocolate products, customers, and orders through a graphical user interface connected to a centralized database. Communication between the client and server is implemented using Java sockets and a custom request-response protocol.

Technologies: 
Java
Java Swing
MySQL
JDBC
Sockets
Multithreading
NetBeans
Three-layer architecture

The system follows a three-layer client-server architecture: 
Client
Graphical user interface (Java Swing)
User authentication
Product, customer, and order management
Communication with the server through sockets

Server: 
Business logic implementation
Request processing
Database access
Concurrent handling of multiple clients using threads

Common: 
Shared domain classes
Request and Response objects
Communication protocol definitions

Features: 
Authentication
Administrator login

Chocolate Management: 
Create chocolate products
Search chocolates
Update chocolate information
Delete chocolates

Order Management: 
Create orders
Search orders
Update orders
Delete orders

Customer Management: 
Customer records
Customer search functionality

Technical Highlights: 
Client-server communication using sockets
Multithreaded server architecture
JDBC database access layer
Transaction management with commit and rollback support
Object serialization for network communication
Dynamic table refresh using background threads

Database Setup: 
Create a MySQL database.
Import the SQL script located in the database folder.
Update database configuration if necessary.
Start the server application.
Launch the client application.

Project Structure: 
Client/
Server/
Common/
database/
docs/

Demo: 
Short video showing the application in action is available in the repository.

Authors
Developed as a university project at the Faculty of Organizational Sciences.
