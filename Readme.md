# Cold Email CRM with Gmail Integration

A Spring Boot application to automate personalized referral emails using Gmail, Gmail, and LLM-generated content.

## Features

- Store recipient details (company email, job links) and email metadata.
- Fetch job descriptions, filter relevant details, and generate emails via LLM.
- Send emails via Gmail and track responses.
- Schedule emails using cron jobs.
- PostgreSQL database with JSONB support for Gmail IDs.

## Tech Stack

- **Backend**: Java Spring Boot
- **Database**: PostgreSQL
- **Email Service**: Gmail APIs
- **ORM**: Spring Data JPA
- **JSONB Handling**: Hibernate Types

## Prerequisites

- Java 17+
- PostgreSQL 14+
- Maven
- Gmail API Key
