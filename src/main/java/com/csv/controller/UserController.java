package com.csv.controller;

import com.csv.dto.UserDto;
import com.csv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/csv")
@CrossOrigin("*")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Uploading file titled {}", file.getOriginalFilename());
        userService.processFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "users")
    public List<UserDto> getAllUsers() {
        log.info("Getting list of users");
        return userService.getAllUsers();
    }

    @GetMapping
    public UserDto getUser(@RequestParam String surname) {
        log.info("Get user by surname = {}", surname);
        return userService.findBySurname(surname);
    }

    @DeleteMapping("{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        log.info("Delete user by ID = {}", userId);
        userService.deleteUserById(userId);
    }

    @DeleteMapping(value = "all")
    public void deleteAllUsers() {
        log.info("Delete all users");
        userService.deleteAllUsers();
    }

    @GetMapping(value = "usersByAge")
    public List<UserDto> getUsersByAge() {
        log.info("Getting list of users by age");
        return userService.usersByAge();
    }

    @GetMapping(value = "oldestUserWithPhone")
    public UserDto getOldestUserWithPhone() {
        log.info("Getting the oldest user with phone number");
        return userService.getTheOldestWithPhone();
    }
}
