# Hypothetical Service
 
## Clean build whole project from command line
```
./gradlew clean build
```

## Check that it is running
```
http://localhost:8080/swagger-ui.html
```

## Generating the Operating Manual

The operating manual is written using the asciidoc format and the source files can be found in the docs directory.
To generate the operating manual the following command is executed in the root of the project.

```
./gradlew asciidoc
```


The operating manual will be rendered in HTML and the output can be located in the build/operating-manual directory.

## Project Structure

### Testing
The Faster payments manager project contains a service module/directory and this is a standard Springboot web project with 
JUnit 5 and JUnit 4 configured. To test the service the following command is used from the root project.

## Running unit tests from command line
```
./sbuild.sh
```
## Running component tests from command line
```
./cbuild.sh
```
## Running all tests from command line
```
./buildAll.sh
```
