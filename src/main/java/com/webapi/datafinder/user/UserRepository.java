package com.webapi.datafinder.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmployeeCode(String employeeCode);

    void deleteByEmployeeCode(String employeeCode);

    boolean existsByEmployeeCode(String employeeCode);

    Optional<User> findByEmail(String email);

    List<User> findAllByUserRole(UserRole userRole);

    Optional<User> findTopByOrderByIdDesc();


}
