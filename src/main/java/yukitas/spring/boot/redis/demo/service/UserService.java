package yukitas.spring.boot.redis.demo.service;

import java.util.List;

import yukitas.spring.boot.redis.demo.model.User;

public interface UserService {
    List<User> getUserByName(String firstName, String lastName);

    User saveUser(String firstName, String lastName);
}
