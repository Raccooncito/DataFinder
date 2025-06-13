package com.webapi.datafinder.user.DTO;

import com.webapi.datafinder.user.UserRole;

public class UserGeneralResponseDto {
    private String employeeCode;
    private String firstName;
    private UserRole userRole;

    public UserGeneralResponseDto() {
    }

    public UserGeneralResponseDto(String employeeCode, String firstName, UserRole userRole) {
        this.employeeCode = employeeCode;
        this.firstName = firstName;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
