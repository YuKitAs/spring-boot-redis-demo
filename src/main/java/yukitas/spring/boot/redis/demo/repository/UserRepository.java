package yukitas.spring.boot.redis.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import yukitas.spring.boot.redis.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<List<User>> findByLastName(String lastName);

    Optional<List<User>> findByFirstNameAndLastName(String firstName, String lastName);
}
