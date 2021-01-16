# Kotlin POC and experiments

Kotlin GraphQL server. Built with the following libraries:

- https://www.http4k.org/ - Web app builder
- https://github.com/JetBrains/Exposed - ORM
- https://github.com/ExpediaGroup/graphql-kotlin - GraphQL builder

## Gradle tasks

- `./gradlew run` to start the project.
  It's the only command you'll need to get started, dependencies will be installed, and migrations performed the first time you run this command.

- `./gradlew compileKotlin --continuous` to compile and watch the Kotlin code only. Pretty fast and convenient.

- `./gradlew test` to run the tests

- `./gradlew tasks` to list all the available tasks

## Requesting the API

You can use an external GraphQL client to send requests to the API on [http://localhost:8000/graphql](http://localhost:8000/graphql).
