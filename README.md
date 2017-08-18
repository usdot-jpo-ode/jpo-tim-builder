# CV TIM Builder
The CV TIM Builder is an open source application built to send test TIMs (traveler information messages) to RSUs through the ODE. 

<a name="toc"/>

## Table of Contents 

[I. Release Notes](#release-notes) 

[II. Documentation](#documentation) 

[III. Getting Started](#getting-started) 

[IV. Running the Application](#running) 

--- 

<a name="release-notes"/>
 
## [I. Release Notes](ReleaseNotes.md)

Coming soon...

<a name="documentation"/>

## II. Documentation
The TIM Builder has been designed to be generic enough to work with any group involved with the CV project. It has been integrated with Docker so that it can be deployed on a system without required a large list of dependencies. 

There are three Docker containers used when deploying this application:

* A MYSQL database which holds data for RSUs, milepost locations, and ITIS codes 
* A Java Spring REST Service which retreives data from the MYSQL database
* An Angular2 web front end for designing TIMs, sending them, and deleting existing TIMs off RSUs

Users will need to provides their own data in CSV files which will need to follow the format specified. More information on data entry can be found here (link to service README).  

This repository will be updated with adjustments to TIM fields.

## III. Getting Started

The following instructions describe the procedure to fetch, build, and run the application. 

### Prerequisites
* Maven: https://maven.apache.org/install.html
* Git: https://git-scm.com/
* Docker: (link)
* Docker Compose: (link)

---
### Obtain the Source Code
The TIM Builder is encompassed in one project. 

#### Step 1 - Clone public repository

Clone the source code from the GitHub repository using Git command:

```bash
git clone https://github.com/Trihydro/jpo-tim-builder.git
(git clone https://github.com/usdot-jpo-ode/jpo-tim-builder.git)
```

## IV. Running the Application
---
### Build and Deploy the Application

The REST service relies on Maven to manage builds.

**Step 1**: Build the REST service

Navigate to the root directory of the service and build:

```bash
 cd service/complete
 mvn clean install
```
This build will run unit tests using an in-memory H2 database created with the SQL script unitTestSql.sql located at service\complete\src\main\resources\db. 

**Step 2**: Build Docker services 

Navigate back to the root directory and build. This may take a few minutes to complete initially.

```bash
 cd ../../
 docker-compose build
```

**Step 3**: Run the application

This command will start the MYSQL, REST service, and web application containers. The application will then be accessable at  `localhost:4200`. 

```bash
 docker-compose up
```

[Back to top](#toc)
