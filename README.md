# GoGreen
GoGreen is a small Spring boot REST application with the following abilities:

	- Exposing CRUD service APIs toward internal development.
	- Exposing CRUD REST APIs toward network clients.
	- Using MongoDB to provide persistency

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
