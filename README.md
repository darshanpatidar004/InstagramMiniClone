#  Instagram – Full Stack Social Media Application

Instagram is a full-stack web application inspired by Instagram.  
It allows users to register, log in, create posts, like posts, follow users, and view a personalized feed.

This project is built using **Spring Boot (Backend)** and **React (Frontend)** to demonstrate real-world full-stack development skills.

---

##  Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security (JWT Authentication)
- MySQL
- Maven

### Frontend
- React
- React Router DOM
- Axios
- CSS

---

##  Features

### Authentication
- User Signup
- User Login
- JWT-based authentication
- Protected routes

### User Features
- View profile
- Follow / Unfollow users
- Followers & Following count

### Post Features
- Create post (image URL + caption)
- Like / Unlike posts
- View feed
- View post details with comments

---

##  Project Structure

### Backend (`instgram`)
src/main/java/com/social/instagram
│
├── controller
├── model
├── repository
├── service
├── config
├── util
├── dto
└── InstagramApplication.java


### Frontend (`instagram-frontend`)
src
├── api
├── components
├── pages
├── App.js
└── index.js



---

##  Backend Setup

##  Database Configuration
Create MySQL database: sql
CREATE DATABASE instagrm_db;

-
## Update application.yaml:

spring.datasource.url=jdbc:mysql://localhost:3306/instagram_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true




## Run Backend
mvn clean
mvn spring-boot:run


## Backend URL:
http://localhost:8080

## Frontend Setup
Install Dependencies
cd instaclone-frontend
npm install
npm install react-router-dom axios
## Run Frontend
npm start


## Frontend URL:
http://localhost:3000


## Authentication Flow

User logs in
Backend generates JWT token
Token is stored in localStorage
Axios interceptor sends token in headers
Protected routes are accessible only to logged-in users

## Learning Outcomes
Built REST APIs using Spring Boot
Implemented JWT authentication
Designed relational database schema
Created protected routes in React
Connected frontend and backend using Axios
Debugged real-world backend & frontend issues


## Fture Improvements
Image upload support
Real-time notifications
Stories feature
UI/UX enhancements


##  Author

Darshan Patidar
B.Tech CSE – 8th Semester
IPS IES Academy, Indore
