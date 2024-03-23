package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Schedule;
import com.swp391.webapp.Service.ScheduleService;
import com.swp391.webapp.dto.ScheduleBusyRequestDTO;
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
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    //Lấy schedule theo host
    @GetMapping("/{hostId}")
    public ResponseEntity<List<Schedule>> getScheduleByHostId(@PathVariable int hostId) {
        List<Schedule> schedule = scheduleService.getScheduleByHostId(hostId);
        return ResponseEntity.ok(schedule);
    }

    //Lưu schedule theo host
    @PostMapping
    public ResponseEntity<Schedule> saveSchedule(@RequestBody ScheduleWorikingRequestDTO schedule) {
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    //Xóa schedule theo id
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    //Update schedule theo ID
    @PutMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable int scheduleId, @RequestBody ScheduleWorikingRequestDTO schedule) {
        scheduleService.updateSchedule(scheduleId, schedule);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
