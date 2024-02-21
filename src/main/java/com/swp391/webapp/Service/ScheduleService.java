package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.ScheduleDTO;
import com.swp391.webapp.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Service methods for Schedule entity

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public ScheduleDTO getScheduleById(int scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    public ScheduleDTO saveSchedule(ScheduleDTO schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    // Additional service methods if needed
}