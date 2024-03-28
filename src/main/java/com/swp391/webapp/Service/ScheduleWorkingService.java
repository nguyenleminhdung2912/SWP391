package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Schedule;
import com.swp391.webapp.Repository.ScheduleWorkingRepository;
import com.swp391.webapp.dto.ScheduleWorikingRequestDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleWorkingService {

    @Autowired
    private ScheduleWorkingRepository scheduleWorkingRepository;

    @Autowired
    AccountUtils accountUtils;

    // ServiceDTO methods for Schedule entity

    public List<Schedule> getAllSchedules() {
        return scheduleWorkingRepository.findAll();
    }

    public Schedule findScheduleByID(int id) {
        return scheduleWorkingRepository.findById(id).get();
    }

    public List<Schedule> getScheduleByHostId(int hostId) {
        return scheduleWorkingRepository.findSchedulesByAccountAccountID(hostId);
    }

    public Schedule save(Schedule schedule) {
        return scheduleWorkingRepository.save(schedule);
    }

    public Schedule saveSchedule(ScheduleWorikingRequestDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setTime(scheduleDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        return scheduleWorkingRepository.save(schedule);
    }

    public void deleteSchedule(int scheduleId) {
        scheduleWorkingRepository.deleteById(scheduleId);
    }

    public Schedule updateSchedule(int id, ScheduleWorikingRequestDTO scheduleBusyRequestDTO) {
        Schedule schedule = scheduleWorkingRepository.findById(id).get();
        schedule.setTime(scheduleBusyRequestDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        return scheduleWorkingRepository.save(schedule);
    }

    // Additional service methods if needed
}
