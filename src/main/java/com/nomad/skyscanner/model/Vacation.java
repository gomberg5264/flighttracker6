package com.nomad.skyscanner.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacation")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate start_date;

    @Column
    private LocalDate end_date;

    @Column
    private Integer day_of_vacation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Integer getDay_of_vacation() {
        return day_of_vacation;
    }

    public void setDay_of_vacation(Integer day_of_vacation) {
        this.day_of_vacation = day_of_vacation;
    }
}
