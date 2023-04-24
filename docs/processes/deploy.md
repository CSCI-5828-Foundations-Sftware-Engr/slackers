# How and when we deploy?

## Deploy to staging
1. We only deploy `main` branch to staging, regardless of the applications
2. We always test it locally before we deploy. 
3. It should have passed the `unit tests` on Github
4. You can use the heroku dashboard to deploy one or more of the applications

## Deploy to production
1. We only deploy what has been deployed in staging and *tested* manually
2. It needs to pass the integration tests on staging.
   Check [integration-tests](integration-tests.md) for more information.
3. You can use the `promote` option to promote such a version to production

## Database initialization
1. We created `DatabaseInit` class to read game info from `game_info.json` into postgres db.
2. We wrote a `unit test` to verify database `credentials`.
3. We wrote another `unit test` to verify the number of statement updates, and content of each statement.
4. We successfully tested the class functionality on local db (correctly added game info from .json file into local db)
