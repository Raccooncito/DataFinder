package com.webapi.datafinder.user;

import com.webapi.datafinder.user.DTO.UserGeneralResponseDto;
import com.webapi.datafinder.user.DTO.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserGeneralResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{role}")
    public List<UserGeneralResponseDto> getFilteredUsersByRole(@PathVariable("role") UserRole role) {
        return userService.getFilteredUsersByRole(role);
    }

    @PostMapping
    public void createUser(@RequestBody UserRequestDto
                                   user) {
        userService.createNewUser(user);
    }

    @DeleteMapping(path = "{employeeCode}")
    public void deleteUser(@PathVariable("employeeCode") String employeeCode) {
        userService.deleteUser(employeeCode);
    }

    @PutMapping(path = "{employeeCode}")
    public void updateUser(@PathVariable("employeeCode") String employeeCode, @RequestBody UserRequestDto user) {
        userService.updateUser(employeeCode, user);
    }
}
