# Iteration Planning
We have broken down tasks such that each technical task should have roughly equivalent complexity, thus the points are equally weighted (i.e. we will count each task as 1 point). 

For our MVP, we anticipate roughly 10 technical tasks for user stories. Given a velocity of 5 (which we selected since we have 6 members and 5 is roughly equal), we should take 2 iterations. Note that this does not include some of our stretch goal features, including creating user accounts and tracking games. There will also likely be additional stories for testing and CI/CD infrastructure.

**Burndown Chart**
![Burndown Chart](hw6_burndown.png)

## Milestone #1 Game Pages

**Anticipated Velocity:** 5

**Actual Velocity:** 5

**Tasks:** Each will have 1 primary task holder, listed below. Most tasks will be done in pairs.
- 33: Ibrahim (pair: Michelle)
- 34: Michelle (pair: Ibrahim)
- 35: Lin (pair: Khaled)
- 36: Khaled (pair: Lin)
- 37: Tuan (pair: Abhishek)
- 44: Abhishek (pair: Tuan)

### Summary: 
- We have a basic webpage that lists all games on the homepage
- Each individual game is clickable to get all related news
- We created middleware for the database so all of our apps have an API to interact with the database
- Database has been created and initialized in all environments
- Testing (see section below for elabortion)
  - Integration tests are in the works
  - All contributions of this iteration were tested manually and with unit tests

### Testing

#### Unit

Unit Tests were written for each of the changesets. 

* `AppTest.kt`, `GetFormTest.kt` **Count : 4**

  Test the routes for Home Page and Contact Page.

* `DatabaseInitTest.kt` **Count: 2**

  Test the database initialization module responsible for populating the required tables with curated data.

* `DBConnectionTest.kt` **Count: 6**

  Tests the Database Connection utilities

* `DBInterfaceTest.kt` **Count: 9**

  Tests the Database Query Interface

We have created unit tests for all components developed so far. Tests are built with Gradle and invoked every time we push via Github Actions.

#### Integration
Our integration test suite is currently under development as part of [Task #44](https://github.com/CSCI-5828-Foundations-Sftware-Engr/slackers/issues/44)
We are planning for  **Ktor Client + Jsoup** based  approach to it. 

#### Manual
Since the **integration** tests are not yet ready, we manually tested our two pages
* Homepage which Lists the games and Game Page which shows details.

## Milestone #2 Game News Feed

**Anticipated Velocity:** 5

**Actual Velocity:** X

**Tasks:** Each will have 1 primary task holder, listed below. Most tasks will be done in pairs.

- 38: Tuan
- 39: Ibrahim & Tuan
- 40: Michelle & Ibrahim
- 41: Lin
- 42: Khaled 
- 44: Abhishek
