package com.webapi.datafinder.Line;

import com.webapi.datafinder.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProductionsLine")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String lineCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = true)
    private User supervisor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LineStatus status;

    @ManyToMany
    @JoinTable(
            name = "line_workers",
            joinColumns = @JoinColumn(name = "line_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> workers = new ArrayList<>();

    public Line() {
    }

    public Line(String lineCode, String city, String state, String country, User supervisor, LineStatus status) {
        this.lineCode = lineCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.supervisor = supervisor;
        this.status = status;
        this.workers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public LineStatus getStatus() {
        return status;
    }

    public void setStatus(LineStatus status) {
        this.status = status;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }
}
