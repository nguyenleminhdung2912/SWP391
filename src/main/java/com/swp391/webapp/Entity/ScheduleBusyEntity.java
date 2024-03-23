package com.swp391.webapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "schedule_busy")
public class ScheduleBusyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_busy_ID")
    private Integer id;

    @Column(name = "date")
    @JsonFormat(pattern="MM-dd-yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    @JsonIgnore
    private AccountEntity account;

    @Column(name = "busy")
    private boolean busy = true;
}
