package yukitas.spring.boot.redis.demo.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import yukitas.spring.boot.redis.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = RedisTestConfiguration.class)
public abstract class AbstractControllerIntegrationTest {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    protected RedisTemplate<String, User> getRedisTemplate() {
        return redisTemplate;
    }

    protected TestRestTemplate getTestRestTemplate() {
        return testRestTemplate;
    }
}
