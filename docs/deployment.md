# Deployment

## Pipeline
Dashboard : [link](https://dashboard.heroku.com/pipelines/c5cf5577-f25c-4549-b98c-ace5269770cb)

We have a staging deployment up and production can be up  on a button-click.

## Database

- We deployed Postgres to staging and production to support data storage.
- The environment variable are `DATABASE_URL`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD`, which are used to connect to Postgres on Heroku.
- The `goodboards.games` relation is used by `basic-server` to hold the game information.
- Heroku Postgres connection document: [link](https://devcenter.heroku.com/articles/connecting-heroku-postgres)
- Heroku Postgres add-on document: [link](https://devcenter.heroku.com/articles/heroku-postgresql#removing-the-add-on)

## Messaging

- We deployed Redis to staging and production to support messaging.
- The environment variable is `REDIS_TLS_URL`, which is used to connect to Redis on Heroku.
- We use Redis list as queue using `LPUSH` and `RPOP` commands as push and pop.
- The list `news:collect-analyze` is used by Collector and Analyzer as messaging queue.
- Heroku Redis connection document: [link](https://devcenter.heroku.com/articles/connecting-heroku-redis#external-connections)
- Heroku Redis add-on document: [link](https://devcenter.heroku.com/articles/heroku-redis#connecting-to-heroku-data-for-redis)


## Staging
Staging : [link](https://slackers-csci-5828-staging-v2.herokuapp.com/)

## Production
Production: [link](https://slackers-csci-5828-product-v1.herokuapp.com/)
