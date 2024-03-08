package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Schedule;
import com.swp391.webapp.Repository.ScheduleRepository;
import com.swp391.webapp.dto.ScheduleRequestDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Schedule saveSchedule(ScheduleRequestDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setTime(scheduleDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        schedule.setBusy(!schedule.isBusy());
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public Schedule updateSchedule(int id, ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = scheduleRepository.findById(id).get();
        schedule.setDate(scheduleRequestDTO.getDate());
        schedule.setTime(scheduleRequestDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        schedule.setBusy(scheduleRequestDTO.isBusy());
        return scheduleRepository.save(schedule);
    }

    // Additional service methods if needed
}
