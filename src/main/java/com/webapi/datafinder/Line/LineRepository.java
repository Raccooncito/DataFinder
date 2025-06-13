package com.webapi.datafinder.Line;

import com.webapi.datafinder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineRepository extends JpaRepository<Line, Long> {
    Optional<Line> findByLineCode(String lineCode);

    List<Line> findAllBySupervisor(User supervisor);

    Optional<Line> findTopByOrderByIdDesc();

    boolean existsBySupervisor(User supervisor);

    boolean existsByLineCode(String lineCode);

    void deleteByLineCode(String lineCode);
}
