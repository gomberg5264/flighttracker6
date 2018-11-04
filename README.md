# NOMAD
This is a coding challenge for skyscanner.
It is a web app designed to help college students to find the best deal to travel during their school breaks. Enter your university name, we find the right time for you to fly.
It is in a very concise form and it is still under active development.

## Technology Stack
1. Spring Boot / MVC / Data
2. Angular
3. Bootstrap
4. Hibernate

## Installation / Requirements
To run, you should have the following technology avaiable.
1. MariaDB(with root password empty for testing)
2. Java 8+

## Compilation Guide
1. `gradle assemble`
2. find the assembed jar file in the build/libs folder
3. `java -jar JAR_FILE_NAME`


## APIs
APIs are public to everyone. You can take a look at the API documentation at `\swagger-ui.html` when you run the server.
You can use the API to install the test data set into the database. In the university_search_controller, click on add_test_data and execute.


## Notice
The schedule of the univeristy is not provided in the source code. We will achieve such a crawling functionality at the later stage.

To use, simply open the webpage at `localhost:8080`, and you can search the University Name. Currently we only have some data fro
1. Jacobs University
2. Uni Bremen
3. Hamburg University
