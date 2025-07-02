package com.webapi.datafinder.Line;

import com.webapi.datafinder.Line.DTO.LineGeneralResponseDto;
import com.webapi.datafinder.Line.DTO.LineRequestDto;
import com.webapi.datafinder.user.DTO.UserGeneralResponseDto;
import com.webapi.datafinder.user.User;
import com.webapi.datafinder.user.UserRepository;
import com.webapi.datafinder.user.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LineService {

    private final LineRepository lineRepository;
    private final UserRepository userRepository;

    public LineService(LineRepository lineRepository, UserRepository userRepository) {
        this.lineRepository = lineRepository;
        this.userRepository = userRepository;
    }

    public List<LineGeneralResponseDto> getAllLines() {
        return lineRepository.findAll().stream()
                .map(line -> {
                    User supervisor = line.getSupervisor();
                    return new LineGeneralResponseDto(
                            line.getLineCode(),
                            line.getCountry(),
                            supervisor != null ? supervisor.getEmployeeCode() : null,
                            supervisor != null ? supervisor.getFirstName() : null,
                            line.getStatus()
                    );
                })
                .toList();
    }

    public List<UserGeneralResponseDto> getAllWorkersOfLine(String lineCode) {
        Line line = lineRepository.findByLineCode(lineCode).orElseThrow(
                () -> new IllegalStateException(
                        "The line with id " + lineCode + " does not exists"
                )
        );
        return line.getWorkers().stream().map(
                user -> new UserGeneralResponseDto(
                        user.getEmployeeCode(),
                        user.getFirstName(),
                        user.getUserRole()
                )
        ).toList();
    }

    public void createNewLine(LineRequestDto lineRequestDto) {

        User supervisor =
                userRepository.findByEmployeeCode(lineRequestDto.getSupervisorCode()).orElseThrow(
                        () -> new IllegalStateException(
                                "Supervisor with code " + lineRequestDto.getSupervisorCode() + " not found."
                        )
                );

        if (lineRepository.existsBySupervisor(supervisor)) {
            throw new IllegalStateException(
                    "Supervisor with code " + lineRequestDto.getSupervisorCode() + " has already assigned a Line"
            );
        }

        Line newProductionLine = new Line(
                createLineCode(
                        lineRequestDto.getCity(),
                        lineRequestDto.getState(),
                        lineRequestDto.getCountry()
                ),
                lineRequestDto.getCity(),
                lineRequestDto.getState(),
                lineRequestDto.getCountry(),
                supervisor,
                lineRequestDto.getStatus()
        );

        lineRepository.save(newProductionLine);
    }

    @Transactional
    public void addNewWorkerToLine(String lineCode, String employeeCode) {
        User worker = userRepository.findByEmployeeCode(employeeCode).orElseThrow(
                () -> new IllegalStateException(
                        "Employee with code " + employeeCode + " not found."
                )
        );

        if (worker.getUserRole() != UserRole.Worker) throw new IllegalStateException(
                "Employee with code " + employeeCode + " is not a worker, can't be assigned."
        );

        Line line = lineRepository.findByLineCode(lineCode).orElseThrow(
                () -> new IllegalStateException(
                        "The line with id " + lineCode + " does not exists"
                )
        );

        if (!line.getWorkers().add(worker)) throw new IllegalStateException(
                "The employee " + employeeCode + " is already assigned on the line " + lineCode
        );
    }

    @Transactional
    public void deleteLine(String lineCode) {
        if (!lineRepository.existsByLineCode(lineCode)) {
            throw new IllegalStateException(
                    "The line with id " + lineCode + " does not exists"
            );
        }
        lineRepository.deleteByLineCode(lineCode);
    }

    @Transactional
    public void removeSupervisorFromLines(User supervisor) {
        List<Line> linesToModify = lineRepository.findAllBySupervisor(supervisor);
        if (linesToModify.isEmpty()) return;
        for (Line line : linesToModify) {
            line.setSupervisor(null);
        }
    }

    @Transactional
    public void removeWorkerFromLine(String lineCode, String employeeCode) {
        User worker = userRepository.findByEmployeeCode(employeeCode).orElseThrow(
                () -> new IllegalStateException(
                        "Employee with code " + employeeCode + " not found."
                )
        );

        if (worker.getUserRole() != UserRole.Worker) throw new IllegalStateException(
                "Employee with code " + employeeCode + " is not a worker, can't be assigned."
        );

        Line line = lineRepository.findByLineCode(lineCode).orElseThrow(
                () -> new IllegalStateException(
                        "The line with id " + lineCode + " does not exists"
                )
        );

        if (!line.getWorkers().remove(worker)) throw new IllegalStateException(
                "The employee " + employeeCode + " is not assigned on the line " + lineCode
        );
    }

    @Transactional
    public void updateLine(String lineCode, LineRequestDto productionLineRequestDto) {
        Line line = lineRepository.findByLineCode(lineCode).orElseThrow(
                () -> new IllegalStateException(
                        "The line with id " + lineCode + " does not exists"
                )
        );

        updateCity(line, productionLineRequestDto.getCity());
        updateState(line, productionLineRequestDto.getState());
        updateCountry(line, productionLineRequestDto.getCountry());
        updateSupervisor(line, productionLineRequestDto.getSupervisorCode());
        updateStatus(line, productionLineRequestDto.getStatus());
        updateLineCode(line);
    }


    private void updateCity(Line line, String newCity) {
        if (newCity == null) return;
        if (Objects.equals(line.getCity(), newCity)) return;

        line.setCity(newCity);
    }

    private void updateState(Line line, String newState) {
        if (newState == null) return;
        if (Objects.equals(line.getState(), newState)) return;

        line.setState(newState);
    }

    private void updateCountry(Line line, String newCountry) {
        if (newCountry == null) return;
        if (Objects.equals(line.getCountry(), newCountry)) return;

        line.setCountry(newCountry);
    }


    private void updateSupervisor(Line line, String newSupervisorCode) {
        if (newSupervisorCode == null) return;
        if (Objects.equals(line.getSupervisor().getEmployeeCode(), newSupervisorCode)) return;
        User supervisor =
                userRepository.findByEmployeeCode(newSupervisorCode).orElseThrow(
                        () -> new IllegalStateException("Supervisor with code " + newSupervisorCode + " not found.")
                );

        line.setSupervisor(supervisor);

    }

    private void updateStatus(Line line, LineStatus newStatus) {
        if (newStatus == null) return;
        if (Objects.equals(line.getStatus(), newStatus)) return;
        if (newStatus == LineStatus.none) throw new IllegalStateException("The status cannot be none");

        line.setStatus(newStatus);

    }

    private String createLineCode(String city, String state, String country) {
        Long lastId = lineRepository.
                findTopByOrderByIdDesc().map(Line::getId).orElse(0L);

        return
                getAcronym(city) +
                        state.charAt(0) +
                        country.charAt(0) +
                        "-" +
                        String.format("%04d", lastId + 1);
    }

    private void updateLineCode(Line line) {
        if (line.getLineCode() == null || line.getLineCode().length() < 10) {
            throw new IllegalArgumentException("Invalid line code format: " + line.getLineCode());
        }
        String citySuffix = getAcronym(line.getCity());

        char stateSuffix = line.getState().charAt(0);

        char countrySuffix = line.getCountry().charAt(0);

        int dashIndex = line.getLineCode().indexOf('-');

        String restOfCode = line.getLineCode().substring(dashIndex);

        line.setLineCode(citySuffix + stateSuffix + countrySuffix + restOfCode);
    }


    private String getAcronym(String word) {
        String vowels = "AEIOU";

        return word.toUpperCase()
                .chars()
                .mapToObj(c -> (char) c)
                .filter(c -> vowels.indexOf(c) == -1)
                .limit(3)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
