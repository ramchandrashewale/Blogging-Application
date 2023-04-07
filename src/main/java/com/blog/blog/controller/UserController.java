package com.blog.blog.controller;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.UserDto;
import com.blog.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
       UserDto createdUser= userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser=userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto=userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtos=userService.getAllUsers();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid ){
        userService.deleteUser(uid);
        return  new ResponseEntity<>(new ApiResponse("User deleted successfully ", true),HttpStatus.OK);
    }

}
