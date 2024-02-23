package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class ScheduleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_ID")
    private Integer scheduleID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountDTO account;

    @Column(name = "working_date")
    private LocalDate workingDate;

    @Column(name = "working_time")
    private LocalTime workingTime;

    @Column(nullable = false)
    private boolean busy;

    // Constructors, getters, setters, etc.
}

