# Development

## Working with Redis locally

- We used official Redis docker image to deploy locally [link](https://hub.docker.com/_/redis)
- Heroku uses Redis version 7.0, so to pull the image, we used `docker  pull redis:7.0`
- To start Redis container, we used `docker run --name goodboards_redis redis:7.0`
- Noted that default port of Redis container is `6379`
- To connect to Redis server, from host terminal, we used `redis-cli -u redis://localhost:6379`
- To test push and pop command, we can use `LPUSH news:collect-analyze "{'name': 'Friendship destroying news headline'}"` and `RPOP news:collect-analyze 10`
