package com.swp391.webapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    @JsonIgnore
    private AccountEntity account;

    @Column(name = "time")
    @JsonFormat(pattern="HH:mm:ss")
    private Time time;

    // Constructors, getters, setters, etc.
}

