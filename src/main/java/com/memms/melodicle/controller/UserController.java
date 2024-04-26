//package com.memms.melodicle.controller;
//
//import com.memms.melodicle.domain.dto.UserDTO;
//import com.memms.melodicle.domain.vo.User;
//import com.memms.melodicle.services.UserService;
//import com.memms.melodicle.utility.UserUtil;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    private UserService userService;
//
//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
//    @Operation(summary = "Create a new user.")
//    @PostMapping("/")
//    public ResponseEntity<UserDTO> createUser(@Validated @Valid @RequestBody User user) {
//        System.out.println("user: " + user);
//        User createdUser = userService.createUser(user);
//        UserDTO createdUserDTO = UserUtil.convertToDTO(createdUser);
//        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Get a user by id.")
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) {
//        User user = userService.getUserById(id);
//        UserDTO userDTO = UserUtil.convertToDTO(user);
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Get a user by id.")
//    @GetMapping("/")
//    public ResponseEntity<UserDTO> getUserById() {
//        long t = 2323;
//        User user = userService.getUserById(t);
//        UserDTO userDTO = UserUtil.convertToDTO(user);
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Get a user by username.")
//    @GetMapping("/username/{username}")
//    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
//        User user = userService.getUserByUsername(username);
//        UserDTO userDTO = UserUtil.convertToDTO(user);
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }
//
//
//}
