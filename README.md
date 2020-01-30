# BBC News Gems

> Application which scans BBC home page for headlines and extracts less common words from them. Then the words are used to generate exercises to practice vocabulary. Oxford Disctionary API is used to retrieve data about the words in question.

## Intended minimal functionality
##### Headlines
 - display current headlines as a list
 - automatically highlight less common words
 - show (tooltip ?) information about the words
 - generate a quiz with those words
 - save the quiz & display it for user to complete
##### User
 - user can register & log in
 - user has access to his custom quizes

## Technologies & Tools
##### Backend
 - Spring Boot 2 
   - Spring Data JPA + Hibernate
   - Spring Data Rest 
   - Spring Data Web
   - Spring Security
 - MySql + Flyway
##### Frontend
 - React
##### Other libraries
 - Lombok
##### Monitoring
- Actuator, Swagger, Hal Explorer
##### Deployment
- Heroku, AWS, Azure ?

### Todos

 - Add MORE quiz generation options
 - Store scores for particular quizes
 - Use an algorithm to optimize memorisation of the vocabulary





License
----

MIT


**Free Software, Hell Yeah!**

