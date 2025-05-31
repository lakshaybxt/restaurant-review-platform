# Restaurant Review Platform

A web application where users can post and search restaurant reviews. The platform is secured with Keycloak for user authentication and leverages Elasticsearch for powerful search capabilities. Users can log in, search for restaurants, and explore nearby restaurants using geolocation powered by [Nominatim OpenStreetMap](https://nominatim.openstreetmap.org/). Reviews can be sorted by date and rating, restaurants by ratings, and users can upload photos with their reviews. Analytics is enabled through Kibana.

---

## Features

- User authentication and authorization via Keycloak
- CRUD operations on restaurant reviews
- Full-text search and geo-location based restaurant discovery
- Sorting reviews by date and rating
- Sorting restaurants by rating
- Upload photos along with reviews
- Analytics and monitoring using Kibana
- Centralized exception handling for robust API error responses

### Main Entities

- **Address**
- **OperatingHours**
- **TimeRange**
- **Review**
- **Restaurant**
- **Photo**

---

## Technology Stack

- **Backend:** Spring Boot, Java, Maven
- **Authentication:** Keycloak
- **Search:** Elasticsearch
- **Analytics:** Kibana
- **Containerization:** Docker, Docker Compose

---

## Getting Started

### Prerequisites

- Docker and Docker Compose installed
- Java 17+ and Maven installed (for running backend separately)

### Installation & Running

1. Clone the repository:

```bash
git clone https://github.com/lakshaybxt/restaurant-review-platform.git
cd restaurant-review-platform
```
### Start all required services using Docker Compose:

```bash
docker-compose up
```
This command will launch Elasticsearch, Kibana, Keycloak, and your Spring Boot backend.

Alternatively, to run the Spring Boot application separately:

```bash
./mvnw spring-boot:run
```

## Usage

- Access the Web UI (URL and details to be added)
- Use REST APIs via Postman or other tools
- Authenticate via Keycloak login portal
- Search restaurants, post reviews, and upload photos
- Explore nearby restaurants based on geolocation

## API Documentation

[Dcumentation]{https://western-aluminum-170.notion.site/Restaurant-Review-Platform-API-Documentation-204e44bc5a7f8029a4e3d3d6153a6ffa}.
