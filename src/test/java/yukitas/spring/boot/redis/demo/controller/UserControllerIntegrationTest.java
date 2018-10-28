package yukitas.spring.boot.redis.demo.controller;

import org.junit.After;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import yukitas.spring.boot.redis.demo.model.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerIntegrationTest extends AbstractControllerIntegrationTest {
    private static final String FIRST_NAME_1 = "John";
    private static final String FIRST_NAME_2 = "Max";
    private static final String LAST_NAME = "Doe";

    @After
    public void tearDown() {
        getRedisTemplate().getConnectionFactory().getConnection().flushDb();
    }

    @Test
    public void saveUserWithFirstNameAndLastName() {
        ResponseEntity<User> response = getTestRestTemplate().postForEntity(
                String.format("/users?first_name=%s&last_name=%s", FIRST_NAME_1, LAST_NAME), null, User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).satisfies(user -> {
            assertThat(user.getFirstName()).isEqualTo(FIRST_NAME_1);
            assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        });
    }

    @Test
    public void getUsersByLastName() {
        getTestRestTemplate().postForEntity(String.format("/users?first_name=%s&last_name=%s", FIRST_NAME_1, LAST_NAME), null, User.class);
        getTestRestTemplate().postForEntity(String.format("/users?first_name=%s&last_name=%s", FIRST_NAME_2, LAST_NAME), null, User.class);

        ResponseEntity<User[]> response = getTestRestTemplate().getForEntity(String.format("/users?last_name=%s", LAST_NAME), User[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).satisfies(users -> {
            Set<String> firtnameSet = Arrays.stream(users).map(User::getFirstName).collect(Collectors.toSet());
            assertThat(firtnameSet.size()).isEqualTo(2);
            assertThat(firtnameSet).containsOnly(FIRST_NAME_1, FIRST_NAME_2);
        });
    }

    @Test
    public void getUsersByFirstNameAndLastName() {
        getTestRestTemplate().postForEntity(String.format("/users?first_name=%s&last_name=%s", FIRST_NAME_1, LAST_NAME), null, User.class);
        getTestRestTemplate().postForEntity(String.format("/users?first_name=%s&last_name=%s", FIRST_NAME_2, LAST_NAME), null, User.class);

        ResponseEntity<User[]> response = getTestRestTemplate().getForEntity(
                String.format("/users?first_name=%s&last_name=%s", FIRST_NAME_2, LAST_NAME), User[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).satisfies(users -> {
            assertThat(users.length).isEqualTo(1);
            assertThat(users[0].getFirstName()).isEqualTo(FIRST_NAME_2);
            assertThat(users[0].getLastName()).isEqualTo(LAST_NAME);
        });
    }
}