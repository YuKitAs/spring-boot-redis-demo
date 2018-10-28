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

Run application with `dev` profile:

```console
$ mvn spring-boot:run -Dspring.profiles.active=dev
```

Base URL: `http://localhost:8081`