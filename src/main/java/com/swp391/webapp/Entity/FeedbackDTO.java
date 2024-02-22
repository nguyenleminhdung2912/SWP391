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
public class FeedbackDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_ID")
    private Integer feedbackId;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    private ServiceDTO service;

    @ManyToOne
    @JoinColumn(name = "guest_ID", nullable = false)
    private GuestDTO guest;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "feedback_date")
    private LocalDate feedbackDate;
}
