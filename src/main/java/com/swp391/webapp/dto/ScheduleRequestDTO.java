package com.swp391.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swp391.webapp.Entity.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {

    private Integer id;

    private Date date;

    private Time time;

    private boolean busy = false;
}
