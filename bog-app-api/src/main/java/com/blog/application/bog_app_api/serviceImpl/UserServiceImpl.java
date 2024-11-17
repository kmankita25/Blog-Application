package com.blog.application.bog_app_api.serviceImpl;

import com.blog.application.bog_app_api.entity.User;
import com.blog.application.bog_app_api.exceptions.ResourceNotFoundException;
import com.blog.application.bog_app_api.payload.UserDto;
import com.blog.application.bog_app_api.repositories.UserRepo;
import com.blog.application.bog_app_api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser= this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userid) {
        User user=this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","Id",userid));
        return this.userToDto(user);
    }

    @Override
   /* public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }*/
    //normal flow of stream api
    public List<UserDto> getAllUsers() {
        // Retrieve all User objects from the repository
        List<User> users = this.userRepo.findAll();

        // Create a new list to hold UserDto objects
        List<UserDto> userDtos = new ArrayList<>();

        // Loop through each User object in the users list
        for (User user : users) {
            // Convert each User object to a UserDto object using userToDto method
            UserDto userDto = this.userToDto(user);

            // Add the UserDto object to the userDtos list
            userDtos.add(userDto);
        }

        // Return the list of UserDto objects
        return userDtos;
    }


    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

    }

  /*  private User dtoToUser(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        return user;
    }*/
  private User dtoToUser(UserDto userDto){
      User user=this.modelMapper.map(userDto,User.class);
      return user;
  }
 /*   private  UserDto userToDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        return userDto;
    }*/
 private  UserDto userToDto(User user)
 {
     UserDto userDto=this.modelMapper.map(user,UserDto.class);
     return userDto;
 }
}
