# Bank JSP Web

A JSP/Servlet-based banking web application with embedded Tomcat 9.

## Requirements

- Java 11+
- Maven 3.6+
- Docker (for PostgreSQL)

## Setup

**1. Start the database**
```bash
docker run -d --name bank-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=1234 \
  -e POSTGRES_DB=Bank \
  -p 5433:5432 postgres:latest
```

**2. Apply schema and seed data**
```bash
docker exec -i bank-postgres psql -U postgres -d Bank < schema.sql
docker exec -i bank-postgres psql -U postgres -d Bank < seed.sql
```

**3. Set email env vars (for OTP)**
```bash
export email=your@gmail.com
export password=your_app_password
```

**4. Build and run**
```bash
mvn clean package
java -jar target/bank-standalone.jar
```

App runs at `http://localhost:8080/bank`

## Sample Credentials

| Role  | Email               | Password      | TOTP Secret          |
|-------|---------------------|---------------|----------------------|
| User  | alice@example.com   | password123   | JBSWY3DPEHPK3PXP     |
| User  | bob@example.com     | password123   | JBSWY3DPEHPK3PXQ     |
| User  | charlie@example.com | password123   | JBSWY3DPEHPK3PXR     |
| Admin | admin1@bank.com     | admin123      | JBSWY3DPEHPK3PXS     |
| Admin | admin2@bank.com     | admin123      | JBSWY3DPEHPK3PXT     |

> Add the TOTP secret to Google Authenticator via "Enter setup key".

## Features

- User signup with email OTP verification
- Login with TOTP-based 2FA (Google Authenticator)
- Fund transfers between accounts
- Withdrawals
- Admin dashboard with user and transaction management
- Export transaction records as PDF or Excel
- Google Sign-In support
