package com.webapi.datafinder.user.DTO;

import com.webapi.datafinder.user.UserRole;

import java.time.LocalDate;

public class UserRequestDto {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
    private UserRole userRole;

    public UserRequestDto() {
    }

    public UserRequestDto(String firstName, String lastName, LocalDate birthday, String email, String password, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
