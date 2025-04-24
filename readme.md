# 🛒 Grocery Management System

A full-stack grocery management system built with **Java Spring Boot**, **Thymeleaf**, **PostgreSQL**, and **Docker**.  
It allows users to manage a grocery item database with features like image generation, filtering, sorting, and searching.

---

## 🚀 Features

- Add and manage grocery items with categories, quantities, units, and prices
- Auto-generate grocery item images using the [Pixabay API](https://pixabay.com/api/docs/)
- Filter items by category
- Search items by name
- Sort items alphabetically (A → Z / Z → A)
- Responsive frontend using Thymeleaf
- PostgreSQL database with Docker support

---

## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot, Spring Data JPA
- **Frontend**: Thymeleaf
- **Database**: PostgreSQL
- **Containerization**: Docker + Docker Compose
- **API Integration**: Pixabay API (for image URLs)
- **Build Tool**: Maven

---

### 🧪 Local Setup

#### 1. Clone the repository

```bash
git clone https://github.com/Kaduhuy/GrocerySystem.git

cd GrocerySystem
```
#### 2. Configure .env file with credentials

```credentials
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
POSTGRES_DB=YOUR_DATABASE_NAME
DB_USER=YOUR_DB_USERNAME
DB_PASS=YOUR_DB_PASSWORD
PIXABAY_API_KEY=YOUR_PIXABAY_API_KEY
```
#### 3. Run with Docker

```bash
docker-compose down -v
docker-compose build
docker-compose up
```
or
```bash
docker-compose up --build
```
#### 4. Accessing the app

```browser
http://localhost:8080/login
```

## 🎯 Intention

This project was created to learn java and it goes through:

- Practice Spring Boot backend development
- Use Thymeleaf to build simple UIs
- Integrate external APIs (pixabay)
- Learn Docker and PostgreSql integration