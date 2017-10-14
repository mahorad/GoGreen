# GoGreen
GoGreen is a small Spring boot REST application with the following properties:

	- Exposes CRUD service APIs for developers.
	- Exposes CRUD REST APIs toward network clients.
	- Uses MongoDB to provide persistency
	- Unit tested using JUnit and Mockito

The application assumes that MongoDB is installed and it's daemon is running in the background.

### Compile Application:
From the project root, run the following command.
```bash
mvn compile
```

### Run Application:
Goto the root folder of the project use the following mathods in order to run the application.

#### Maven Command:

```bash
mvn spring-boot:run
```

#### Executable JAR:
Generate the executable jar file in the target folder using the following command.

```bash
mvn clean package 
```

Run the jar file as demonstrated below:

```bash
java -jar target/GoGreen-1.0-SNAPSHOT.jar
```
