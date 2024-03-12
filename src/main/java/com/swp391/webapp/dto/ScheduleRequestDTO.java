package com.swp391.webapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm")
    private Time time;

    private boolean busy = false;
}
