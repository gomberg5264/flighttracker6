package com.nomad.skyscanner.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_university")
    private List<Vacation> available_vacations;

    @Column
    private String location_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vacation> getAvailable_vacations() {
        return available_vacations;
    }

    public void setAvailable_vacations(List<Vacation> available_vacations) {
        this.available_vacations = available_vacations;
    }

    public String getLocation_code() {
        return location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }
}
