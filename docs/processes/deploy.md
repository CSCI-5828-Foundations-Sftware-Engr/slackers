# How and when we deploy?

## Deploy to staging
1. We only deploy `main` branch to staging, regardless of the applications
2. We always test it locally before we deploy. 
3. It should have passed the `unit tests` on Github
4. You can use the heroku dashboard to deploy one or more of the applications

## Deploy to production
1. We only deploy what has been deployed in staging and *tested* manually
2. It needs to pass the integration tests on staging.
3. You can use the `promote` option to promote such a version to production
