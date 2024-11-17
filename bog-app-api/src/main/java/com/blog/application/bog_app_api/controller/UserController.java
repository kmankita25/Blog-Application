package com.blog.application.bog_app_api.controller;

import com.blog.application.bog_app_api.payload.ApiResponse;
import com.blog.application.bog_app_api.payload.UserDto;
import com.blog.application.bog_app_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired

    private UserService userService;

    //Post-create user
    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }
@PutMapping("/{userId}")
    public ResponseEntity<UserDto>updatedUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId )
{
    UserDto updateDto=this.userService.updateUser(userDto,userId);
    return  ResponseEntity.ok(updateDto);
}

    //Put-update user
    @DeleteMapping("/{userId}")
        public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
/*        return new ResponseEntity(Map.of("message","user deleted successfully"), HttpStatus.OK);*/
        return new ResponseEntity(new ApiResponse("user deleted Successfully",true), HttpStatus.OK);

}

    //Delete- delete user


    //Get-user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());

    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
