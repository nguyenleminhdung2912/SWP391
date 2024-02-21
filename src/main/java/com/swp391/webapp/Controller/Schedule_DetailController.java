package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Schedule_Detail_DTO;
import com.swp391.webapp.Service.Schedule_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-details")
public class Schedule_DetailController {

    @Autowired
    private Schedule_DetailService schedule_DetailService;

    @GetMapping
    public ResponseEntity<List<Schedule_Detail_DTO>> getAllScheduleDetails() {
        List<Schedule_Detail_DTO> scheduleDetails = schedule_DetailService.getAllScheduleDetails();
        return ResponseEntity.ok(scheduleDetails);
    }

    @GetMapping("/{scheduleDetailId}")
    public ResponseEntity<Schedule_Detail_DTO> getScheduleDetailById(@PathVariable int scheduleDetailId) {
        Schedule_Detail_DTO scheduleDetail = schedule_DetailService.getScheduleDetailById(scheduleDetailId);
        return ResponseEntity.ok(scheduleDetail);
    }

    @PostMapping
    public ResponseEntity<Schedule_Detail_DTO> saveScheduleDetail(@RequestBody Schedule_Detail_DTO scheduleDetail) {
        Schedule_Detail_DTO savedScheduleDetail = schedule_DetailService.saveScheduleDetail(scheduleDetail);
        return ResponseEntity.ok(savedScheduleDetail);
    }

    @DeleteMapping("/{scheduleDetailId}")
    public ResponseEntity<Void> deleteScheduleDetail(@PathVariable int scheduleDetailId) {
        schedule_DetailService.deleteScheduleDetail(scheduleDetailId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
