package yukitas.spring.boot.redis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import yukitas.spring.boot.redis.demo.exception.ResourceNotFoundException;
import yukitas.spring.boot.redis.demo.model.User;
import yukitas.spring.boot.redis.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(String firstName, String lastName) {
        return userRepository.save(new User(UUID.randomUUID().toString(), firstName, lastName));
    }

    @Override
    public List<User> getUserByName(String firstName, String lastName) {
        if (firstName == null) {
            return getUserByLastName(lastName);
        }

        return userRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with first name [%s] and last name [%s] not found", firstName, lastName)));
    }

    private List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with last name [%s] not found", lastName)));
    }
}
