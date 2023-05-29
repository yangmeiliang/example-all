package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.domain.entity.User;
import org.example.domain.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaml
 * @since 2021/8/17
 */
@RestController
@AllArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @PostMapping("/users/save")
    public User save(@RequestBody User user) {
        return userMapper.save(user);
    }

    @GetMapping("/users/{id}")
    public User find(@PathVariable Integer id) {
        return userMapper.findById(id).orElse(null);
    }

    @PostMapping("/users/del")
    public void del(@RequestBody User user) {
        userMapper.delete(user);
    }

}
