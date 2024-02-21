package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule_Detail_DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_detail_ID")
    private Long scheduleDetailId;

    @ManyToOne
    @JoinColumn(name = "schedule_ID", nullable = false)
    private ScheduleDTO schedule;

    @ManyToOne
    @JoinColumn(name = "host_ID", nullable = false)
    private HostDTO host;
}
