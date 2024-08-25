# E-commerce Web Application

This project is a Spring Boot-based e-commerce web application designed to streamline business operations for an online store. The application handles product management, customer orders, and payment processing, aiming to improve efficiency and scalability for e-commerce businesses.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Technical Requirements](#technical-requirements)
- [Docker Setup](#docker-setup)

## Introduction

As a business owner in the e-commerce sector, managing various products and customer interactions manually can be time-consuming and prone to errors. This application was developed to automate these processes, allowing for smoother operations and improved customer satisfaction.

## Features

- **Product Management**: Manage a variety of products, each identified by a unique code and accompanied by a detailed description.
- **Customer Management**: Store customer information including first name, last name, email, and address.
- **Order Processing**: Customers can place orders from the product list.
- **Payment Handling**: Process customer payments with multiple payment methods.
- **Email Notifications**: Automatically send email notifications to customers regarding payment status (success or failure).

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/ecommerce-app.git
   cd ecommerce-app
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   
## Usage

Once the application is up and running, you can:

- Add, update, or delete products.
- Manage customer information.
- Process orders and handle payments.

## Technical Requirements

- **Java 17**: The application is built using Java 17, which is required for running the application.
- **Maven 3.6+**: Used for project management and build automation.
- **Spring Boot 3.1.x**: The framework used to build the application.
- **Docker**: For containerization and simplified deployment.
- **Docker Compose**: To manage multi-container Docker applications.

## Docker Setup

Run the application using docker-compose:

   ```bash
   docker-compose up -d
   ```


