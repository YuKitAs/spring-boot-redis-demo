package yukitas.spring.boot.redis.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("user")
public class User {
    @Id
    private final String id;

    @Indexed
    private final String firstName;

    @Indexed
    private final String lastName;

    @JsonCreator
    public User(@JsonProperty("id") String id, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
