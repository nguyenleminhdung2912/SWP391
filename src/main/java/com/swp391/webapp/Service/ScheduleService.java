package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Schedule;
import com.swp391.webapp.Repository.ScheduleRepository;
import com.swp391.webapp.dto.ScheduleBusyRequestDTO;
import com.swp391.webapp.dto.ScheduleWorikingRequestDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    AccountUtils accountUtils;

    // ServiceDTO methods for Schedule entity

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule findScheduleByID(int id) {
        return scheduleRepository.findById(id).get();
    }

    public List<Schedule> getScheduleByHostId(int hostId) {
        return scheduleRepository.findSchedulesByAccountAccountID(hostId);
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule saveSchedule(ScheduleWorikingRequestDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setTime(scheduleDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public Schedule updateSchedule(int id, ScheduleWorikingRequestDTO scheduleBusyRequestDTO) {
        Schedule schedule = scheduleRepository.findById(id).get();
        schedule.setTime(scheduleBusyRequestDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        return scheduleRepository.save(schedule);
    }

    // Additional service methods if needed
}
