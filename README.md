The backend part of the platform for selling goods.
-

Technical specifications used for development: https://github.com/BizinMitya/front-react-avito/blob/v1.19/openapi.yaml

The following functionality was implemented during the development process:
-
- Authorization and authentication of users;
- Restriction on roles between users: user and administrator;
- CRUD operations for announcements and comments on the site: the administrator can delete or edit all announcements and comments, and users can only delete their own;
- Possibility for an authorized user to leave comments under each advertisement;
- Display and save pictures of advertisements and user avatars

Technologies used in the project:
-
- Java 11
- Maven
- Spring Boot
- Spring Data
- Spring Security
- GIT
- REST
- Lombok
- PostgreSQL
- Liquibase
- JUnit
- Mockito

Instructions for installing and launching the application:
-
- Clone the project into the development environment;
- Enter data from application.orogin into application.properties and substitute data for datasource.url, datasource.username, datasource.password;
- Run the Docker image (docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.19) or use Postman;
- Run the HomeworkApplication class;
- Default administrator login and password: login: superadmin@example.com password: 123456789
- happy shopping and successful sales!

The project was developed by:
-
Team "TEAM"
- Begali - b_mashrapov@mail.ru;
- Daria - pazuzu1995@gmail.com;
- Kirill - kiruha5854@mail.ru;
