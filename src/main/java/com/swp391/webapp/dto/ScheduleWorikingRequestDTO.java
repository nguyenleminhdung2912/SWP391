package com.swp391.webapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;

@Data
public class ScheduleWorikingRequestDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm")
    private Time time;

}
