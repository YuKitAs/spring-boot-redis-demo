package yukitas.spring.boot.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import yukitas.spring.boot.redis.demo.model.User;
import yukitas.spring.boot.redis.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName) {
        return new ResponseEntity<>(userService.saveUser(firstName, lastName), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserByName(@RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam("last_name") String lastName) {
        return new ResponseEntity<>(userService.getUserByName(firstName, lastName), HttpStatus.OK);
    }
}