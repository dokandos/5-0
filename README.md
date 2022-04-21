# 5-0

This is a personal project aimed to create an app (hopefully fullstack later on) for a traditional game that many Latin Americans play with their families or friends: A football (soccer) betting pool ("polla" in Colombia).

This app is built based on a microservice architecture, having multiple packages for each microservice. The plan is to have the following microservices:
* Match info ingestor: Ingest data from matches from the following webpage: https://dashboard.api-football.com/ which has multiple endpoints to get football data. This service consumes APIs.
* Score Receiver: Service that will handle the scores created by the users, that is, when a user inputs a score for a specific match
* BettingPoolScores: Service that will handle leaderboards and points for each member of the betting pool. Note that the can be many beeting pools for the same tournament.
* (Review if needed) Kafka: Service that will handle kafka communication between microservices. 

Language: Java
Framework: Spring Boot, Spring Data
Testing libraries: JUnit 5, Mockito
Other tools: Kafka, AWS, GitLab CI/CD

