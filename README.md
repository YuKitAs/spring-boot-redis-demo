# spring-boot-redis-demo

Install and run Redis image:

```console
$ docker pull redis
$ docker run --name redis-local -p 6379:6379 -d redis
```

Install `redis-cli`:

```console
$ sudo apt install redis-tools
```

Run application:

```console
$ mvn spring-boot:run 
```

Base URL: `http://localhost:8081`

Health check: `http://localhost:9000/health`