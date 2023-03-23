# This is Goodboards 
The website where you will find your next favorite board game

## HW5 Deliverables
1. You can find the chart at [Link to be updated]()
2. We have a [**Heroku** pipeline](#deployment) with [app in staging](#staging) as of now
    * We have a submittable form as `GET` [`/form`](https://slackers-csci-5828-staging-v2.herokuapp.com/form) (Click to go)
    * When submitted as `PUT` `/form` it will show page containing the information
    * It additionally maintains a in-memory list of the strings.
    * You can see and sign-up for the exclusive public wait list for the app this way :D

    
### Thanks to [Continuum Collective](co-collective-LICENSE) for Kotlin-ktor-starter base project 

An [application continuum](https://www.appcontinuum.io/) style example using Kotlin and Ktor
that includes a single web application with two background workers.

* Basic web application
* Data analyzer
* Data collector

### Technology stack

This codebase is written in a language called [Kotlin](https://kotlinlang.org) that is able to run on the JVM with full
Java compatibility.
It uses the [Ktor](https://ktor.io) web framework, and runs on the [Netty](https://netty.io/) web server.
HTML templates are written using [Freemarker](https://freemarker.apache.org).
The codebase is tested with [JUnit](https://junit.org/) and uses [Gradle](https://gradle.org) to build a jarfile.

### Deployment
We are deploying our applications in Heroku due to ease of use and free student credits :D

#### Pipeline
Dashboard : [link](https://dashboard.heroku.com/pipelines/c5cf5577-f25c-4549-b98c-ace5269770cb)

We have a staging deployment up and production can be up  on a button-click. 

#### Staging
Staging : [link](https://slackers-csci-5828-staging-v2.herokuapp.com/)

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

### Data collector (WIP)

```bash
java -jar applications/data-collector-server/build/libs/data-collector-server-1.0-SNAPSHOT.jar
```

### Data analyzer (WIP)

```bash
java -jar applications/data-analyzer-server/build/libs/data-analyzer-server-1.0-SNAPSHOT.jar
```

## [Docs](docs/index.md)