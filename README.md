# This is Goodboards 
The website where you will find your next favorite board game

## HW5 Deliverables
1. We have a [roadmap](#roadmap), you can see a zoomed out snapshot [here](docs/hw5_gantt.png).
2. We have a [**Heroku** pipeline](#deployment) with app in [staging](#staging)  and [production](#production)as of now
    * We have a submittable form as `GET` [`/form`](https://slackers-csci-5828-staging-v2.herokuapp.com/form) (Click to go)
    * When submitted as `PUT` `/form` it will show page containing the information
    * It additionally maintains a in-memory list of the strings.
    * You can see and sign-up for the exclusive public wait list for the app this way :D

## Project management
### Project: 
Stories : [link](https://github.com/orgs/CSCI-5828-Foundations-Sftware-Engr/projects/3/)

### Roadmap
 Board: [link](https://github.com/orgs/CSCI-5828-Foundations-Sftware-Engr/projects/3/views/3)
    
### Thanks to [Continuum Collective](co-collective-LICENSE) for Kotlin-ktor-starter base project 

An [application continuum](https://www.appcontinuum.io/) style example using Kotlin and Ktor
that includes a single web application with two background workers.

* Basic web application
* Data analyzer
* Data collector

### Technology stack

Details: [link](technologies.md)

### Deployment
We are deploying our applications in Heroku due to ease of use and free student credits :D

Details : [link](docs/deployment.md)

## Development

1.  Build a Java Archive (jar) file.
    ```bash
    ./gradlew clean build
    ```

1.  Configure the port that each server runs on.
    ```bash
    export PORT=8881
    ```

Run the servers locally using the below examples.

### Web application

```bash
java -jar applications/basic-server/build/libs/basic-server-1.0-SNAPSHOT.jar
```

### Data collector

```bash
java -jar applications/data-collector-server/build/libs/data-collector-server-1.0-SNAPSHOT.jar
```

### Data analyzer (WIP)

```bash
java -jar applications/data-analyzer-server/build/libs/data-analyzer-server-1.0-SNAPSHOT.jar
```

## [Docs](docs/README.md)