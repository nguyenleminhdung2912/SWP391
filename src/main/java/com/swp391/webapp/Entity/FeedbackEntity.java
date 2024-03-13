package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_ID")
    private Integer feedbackID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    private ServiceEntity service;

    @Column
    private String description;

    @Column(name = "rating")
    private int rating;

    @Column(name = "feedback_date")
    private LocalDate feedbackDate;

    // Constructors, getters, setters, etc.
}

