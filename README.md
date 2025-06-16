# UserManagementSystem
Project: Implementing Environment-Specific Logging with Spring Profiles

How to set up the project:
Update the DataBase configuration properties in the application.properties file

How to run the application:
1. Create the DB in MySQL
2. Run the UserManagementApiApplication class.
3. Specify the active profile (dev,test,prod) in application.properties file

NOTE: The server port and logs level will change according with the active profile:
dev -> server port: 8081, logs level: DEBUG
test -> server port: 8082, logs level: INFO
prod -> server port: 8080, logs level: WARN

LOGS for dev and test profiles will be showed on the console and for prod profile will be saved in "logs/app.log" file. You can modify this name in logback-spring.xml file.

API endpoints and sample requests for testing:

TO DISPLAY CONFIGURATIONS (dataSourceUrl, logLevel and serverPort)
- GET -> http://localhost:8081/api/users/configurations

CREATE:
 - POST -> http://localhost:8081/api/users
 - BodyRequest:
{
  "name": "Alice Johnson",
  "email": "alice@example.com"
}
NOTE: To get a WARN log just remove the '@' from the email

READ:
- GET -> http://localhost:8081/api/users/1 (get user by ID)
- GET -> http://localhost:8081/api/users (get all users)

UPDATE:
- PUT -> http://localhost:8081/api/users/1
 - BodyRequest:
{
  "name": "Alice Smith",
  "email": "alice@example.com"
}
NOTE: To get a WARN log just remove the '@' from the email and to get an ERROR log just put in the URL an ID that does not exist.

DELETE:
- DELETE -> http://localhost:8081/api/users/1
