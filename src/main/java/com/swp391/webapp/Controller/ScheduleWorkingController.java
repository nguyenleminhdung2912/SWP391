package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Schedule;
import com.swp391.webapp.Service.ScheduleWorkingService;
import com.swp391.webapp.dto.ScheduleWorikingRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/schedules")
@SecurityRequirement(name = "api")
public class ScheduleWorkingController {

    @Autowired
    private ScheduleWorkingService scheduleWorkingService;

    //Lấy schedule theo host
    @GetMapping("/{hostId}")
    public ResponseEntity<List<Schedule>> getScheduleByHostId(@PathVariable int hostId) {
        List<Schedule> schedule = scheduleWorkingService.getScheduleByHostId(hostId);
        return ResponseEntity.ok(schedule);
    }

    //Lưu schedule theo host
    @PostMapping
    public ResponseEntity<Schedule> saveSchedule(@RequestBody ScheduleWorikingRequestDTO schedule) {
        Schedule savedSchedule = scheduleWorkingService.saveSchedule(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    //Xóa schedule theo id
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int scheduleId) {
        scheduleWorkingService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    //Update schedule theo ID
    @PutMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable int scheduleId, @RequestBody ScheduleWorikingRequestDTO schedule) {
        scheduleWorkingService.updateSchedule(scheduleId, schedule);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
