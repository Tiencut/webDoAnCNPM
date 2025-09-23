package com.codecnpm.doancnpm.service;

import java.util.List;

import com.codecnpm.doancnpm.dto.UserDto;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);

    public static void main(String[] args) {
        UserService userService = null; // You'll need to inject/instantiate actual implementation
        List<UserDto> users = userService.getAllUsers();
        
        if (users != null && !users.isEmpty()) {
            System.out.println("Found users:");
            for (UserDto user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found");
        }
    }
}