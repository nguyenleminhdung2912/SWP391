package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Schedule_Detail_DTO;
import com.swp391.webapp.Repository.Schedule_Detail_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Schedule_DetailService {

    @Autowired
    private Schedule_Detail_Repository schedule_DetailRepository;

    // Service methods for Schedule_Detail entity

    public List<Schedule_Detail_DTO> getAllScheduleDetails() {
        return schedule_DetailRepository.findAll();
    }

    public Schedule_Detail_DTO getScheduleDetailById(int scheduleDetailId) {
        return schedule_DetailRepository.findById(scheduleDetailId).orElse(null);
    }

    public Schedule_Detail_DTO saveScheduleDetail(Schedule_Detail_DTO scheduleDetail) {
        return schedule_DetailRepository.save(scheduleDetail);
    }

    public void deleteScheduleDetail(int scheduleDetailId) {
        schedule_DetailRepository.deleteById(scheduleDetailId);
    }

    // Additional service methods if needed
}