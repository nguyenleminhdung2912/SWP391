package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.ScheduleDTO;
import com.swp391.webapp.Service.ScheduleService;
import com.swp391.webapp.dto.ScheduleRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/schedules")
@SecurityRequirement(name = "api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleByHostId(@PathVariable int hostId) {
        List<ScheduleDTO> schedule = scheduleService.getScheduleByHostId(hostId);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> saveSchedule(@RequestBody ScheduleRequestDTO schedule) {
        ScheduleDTO savedSchedule = scheduleService.saveSchedule(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
