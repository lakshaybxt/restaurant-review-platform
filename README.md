# 🍽️ Restaurant Review Platform

A full-stack backend system where users can review and search restaurants. Built with Spring Boot, secured with Keycloak, and integrated with Elasticsearch for advanced search.

## 🔧 Tech Stack
- Java 17
- Spring Boot
- Docker
- Maven
- Keycloak (OAuth2)
- Elasticsearch & Kibana
- PostgreSQL / MySQL

## ✅ Features
- User registration and login via Keycloak
- Role-based access (User, Admin)
- Submit, update, delete reviews
- Search restaurants by name, cuisine, or rating (powered by Elasticsearch)
- Logs visualized using Kibana
- Containerized using Docker

## 📸 Screenshots / API Docs 
(Later)

## 🐳 How to Run
1. Clone the repo
2. Run `docker-compose up --build`
3. Access Keycloak at `localhost:8080/auth`
4. Access API at `localhost:8080/api/v1/...`

## 🧠 Learning
This project helped me understand production-ready practices including:
- OAuth2 with Keycloak
- Log monitoring with Kibana
- Full Dockerized microservice-like setup

