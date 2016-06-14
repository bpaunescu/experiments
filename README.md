# experiments

A simple multi threaded web server made in Java. Handles HTTP GET requests. No keep alive implemented yet. Good for deploying small static
websites.

Type of resources which are served: text, html, jpg, png, js, css. Other types can be easily added by changing ContentType.java
For some quick tests, the collection of files inside the Website directory can be used. The entry point is index.html

The application was developed in Eclipse using Maven. to build just execute the following commands:
- mvn clean compile assembly:single
- mvn install
This will create a .jar file called simplewebserver-0.0.1-SNAPSHOT-jar-with-dependencies in the target directory. The .jar has been made available
with the code as well.

Configuring and launching the application:
- a simple batch file(launch.bat) has been made available
- edit the batch file with properties for the web server(port number, number of threads to be used, web app root directory, web app home page)
- execute the batch file
