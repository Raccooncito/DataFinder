package com.webapi.datafinder.user.DTO;

import com.webapi.datafinder.user.UserRole;

import java.time.LocalDate;

public class UserDetailedResponseDto {
    private String employeeCode;
    private String firstName;
    private String lasName;
    private LocalDate birthday;
    private String email;
    private String password;
    private UserRole userRole;

    public UserDetailedResponseDto() {
    }

    public UserDetailedResponseDto(String employeeCode, String firstName, String lasName, LocalDate birthday, String email, String password, UserRole userRole) {
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lasName = lasName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }


    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
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
