CRUD implementation for vehicle and its wheels using Spring Boot.
Main depedencies used:
* Spring Boot
* Mapstruct for mapping DTOs to entites and vice versa
* Lombok for generating boilerplate code

## Getting Started

* Open terminal
* Navigate to project main directory
* Run `mvn spring-boot:run`

### Prerequisites

* Java 8
* Maven 3.*
* MariaDB instance (username and password are configurable in application.properties)

## Running the tests

In order to run all the tests, navigate to the project main directory.

Run `mvn test`

Test methods are located in `src/test/java/lsh.assignment.rest/RestApplicationTests.java`

## Extra notes
* For CRUD validation, Javax annotation validations are used in combination with custom validations.
Validations errors are registered in a BindingResult, which is returned in a 400 Bad Request Response.
* Wheel null properties validation is tested with a small integratrion test using MockMvc.
