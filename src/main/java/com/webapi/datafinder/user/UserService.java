package com.webapi.datafinder.user;

import com.webapi.datafinder.Line.LineService;
import com.webapi.datafinder.user.DTO.UserGeneralResponseDto;
import com.webapi.datafinder.user.DTO.UserRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final LineService lineService;

    @Autowired
    public UserService(UserRepository userRepository, LineService LineService) {
        this.userRepository = userRepository;
        this.lineService = LineService;
    }

    public List<UserGeneralResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserGeneralResponseDto(
                        user.getEmployeeCode(),
                        user.getFirstName(),
                        user.getUserRole()
                ))
                .toList();
    }

    public List<UserGeneralResponseDto> getFilteredUsersByRole(UserRole role) {
        return userRepository.findAllByUserRole(role).stream()
                .map(user -> new UserGeneralResponseDto(
                        user.getEmployeeCode(),
                        user.getFirstName(),
                        user.getUserRole()
                ))
                .toList();
    }

    public void createNewUser(UserRequestDto newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already in use");
        }

        userRepository.save(
                new User(
                        createEmployeeCode(
                                newUser.getFirstName(),
                                newUser.getLastName(),
                                newUser.getBirthday()
                        ),
                        newUser.getFirstName(),
                        newUser.getLastName(),
                        newUser.getBirthday(),
                        newUser.getEmail(),
                        newUser.getPassword(),
                        newUser.getUserRole()
                )
        );
    }

    @Transactional
    public void deleteUser(String employeeCode) {
        User userToDelete = userRepository.findByEmployeeCode(employeeCode).orElseThrow(() ->
                new IllegalStateException((
                        "The user with id " + employeeCode + " does not exists"
                )));
        if (userToDelete.getUserRole().equals(UserRole.Supervisor))
            lineService.removeSupervisorFromLines(userToDelete);

        userRepository.delete(userToDelete);
    }

    @Transactional
    public void updateUser(String employeeCode, UserRequestDto userToUpdate) {
        User user = userRepository.findByEmployeeCode(employeeCode).orElseThrow(() -> new IllegalStateException((
                "The user with id " + employeeCode + " does not exists"
        )));

        updateFirstName(user, userToUpdate.getFirstName());
        updateLastName(user, userToUpdate.getLastName());
        updateBirthday(user, userToUpdate.getBirthday());
        updateEmail(user, userToUpdate.getEmail());
        updatePassword(user, userToUpdate.getPassword());
        updateUserRol(user, userToUpdate.getUserRole());
        updateEmployeeCode(user);
    }

    private void updateFirstName(User user, String firstName) {
        if (firstName == null || firstName.isEmpty()) return;
        if (Objects.equals(user.getEmail(), firstName)) return;

        user.setFirstName(firstName);
    }

    private void updateLastName(User user, String lastName) {
        if (lastName == null || lastName.isEmpty()) return;
        if (Objects.equals(user.getEmail(), lastName)) return;

        user.setFirstName(lastName);
    }

    private void updateBirthday(User user, LocalDate birthday) {
        if (birthday == null) return;
        if (Objects.equals(user.getBirthday(), birthday)) return;

        user.setBirthday(birthday);
    }

    private void updateEmail(User user, String email) {
        if (email == null || email.isEmpty()) return;
        if (Objects.equals(user.getEmail(), email)) return;
        if (userRepository.findByEmail(email).isPresent()) throw new IllegalArgumentException("email is already taken");

        user.setEmail(email);
    }


    private void updatePassword(User user, String password) {
        if (password == null || password.isEmpty()) return;
        if (Objects.equals(user.getPassword(), password)) return;

        user.setPassword(password);
    }

    private void updateUserRol(User user, UserRole userRole) {
        if (userRole == null) return;
        if (userRole == UserRole.None) throw new IllegalArgumentException("The role can't be None");
        if (Objects.equals(user.getUserRole(), userRole)) return;

        user.setUserRole(userRole);
    }

    private String createEmployeeCode(String firstName, String lastName, LocalDate birthday) {
        Long lastId = userRepository.
                findTopByOrderByIdDesc().map(User::getId).orElse(0L);

        return
                firstName.substring(0, 2).toUpperCase() +
                        lastName.substring(0, 2).toUpperCase() +
                        String.valueOf(birthday.getYear()).substring(2) +
                        String.valueOf(LocalDate.now().getYear()).substring(2) +
                        "-" +
                        String.format("%04d", lastId + 1);
    }

    private void updateEmployeeCode(User user) {
        if (user.getEmployeeCode() == null || user.getEmployeeCode().length() < 12) {
            throw new IllegalArgumentException("Invalid employee code format: " + user.getEmployeeCode());
        }
        String initials = user.getFirstName().substring(0, 2).toUpperCase() +
                user.getLastName().substring(0, 2).toUpperCase();

        String birthYearSuffix = String.valueOf(user.getBirthday().getYear()).substring(2);

        String restOfCode = user.getEmployeeCode().substring(6);

        user.setEmployeeCode(initials + birthYearSuffix + restOfCode);
    }

}
