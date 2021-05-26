# Travel Agency, A Fullstack Webapplication for booking systems 
This is a fullstack webapplication that focuses testing, security, and developement on the backend side of a enterprise system. It contains a set of examples related to testing, security and development of enterprise systems. Currently, this repository focuses on Java, targeting frameworks like Spring and Java EE.

For building GUIs, the repository
Quizgame-SAP-FullStack-Webapp-in-ReactJS' (https://github.com/Rosso84/Quizgame-SAP-FullStack-Webapp-in-ReactJS) should be used. It relies on knowledge of JavaScript and Single-Page-Applications and focuses more on frontend side of a webapp.
This enterprise application uses JFS as frontend simply because I am still learning and following the materials of my former teachers materials (which can be viewed here: https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/doc/intro/main.md).

## Requirements
JDK 11 (download it from https://adoptopenjdk.net/, do not use the JDK from Oracle)

An IDE (recommended IntelliJ IDEA Ultimate Edition)

Maven 3.x

Docker

## How to install and run locally
Ones you have maven installed open up terminal and run 

 >mvn clean install

You can either run it locally by navigating to frontend/src/test/java/org/studentnr
and right click on LocalApplicationRunner.class

or run with maven command:

>mvn spring-boot:run

once you are inside root of frontend (travelagency/frontend/).

The application will the be available at http://localhost:8080.

## How to run with docker
The application has a Dockerfile set up ready for deployment to cloud services such as Kubernetes or container instances/registries and has been tested at Heroku. 

## Usefull docker commands
View available built/images in your machine:
>Docker ps
>
>Docker images

Removing images:
>Docker volume prune 
>
>Docker system prune -a








