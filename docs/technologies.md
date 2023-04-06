# Technologies

| Component            | Choice            |
|----------------------|-------------------|
| Programming Language | Kotlin            |
| Web Framework        | KTor              |
| Web Server           | Netty             |
| Templating           | Apache FreeMarker |
| Build                | Gradle            |
| Application Platform | Heroku            |
| Database             | PostgreSQL        |
| Queue                | Redis             | 


This codebase is written in a language called [Kotlin](https://kotlinlang.org) that is able to run on the JVM with full
Java compatibility.
It uses the [Ktor](https://ktor.io) web framework, and runs on the [Netty](https://netty.io/) web server.
HTML templates are written using [Freemarker](https://freemarker.apache.org).
The codebase is tested with [JUnit](https://junit.org/) and uses [Gradle](https://gradle.org) to build a jarfile.