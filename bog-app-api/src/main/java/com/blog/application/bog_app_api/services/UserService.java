package com.blog.application.bog_app_api.services;

import com.blog.application.bog_app_api.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userid);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
