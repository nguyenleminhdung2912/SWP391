package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.ScheduleDTO;
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

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleDTO> getScheduleByHostId(int hostId) {
        return scheduleRepository.findScheduleDTOsByAccountAccountID(hostId);
    }

    public ScheduleDTO saveSchedule(ScheduleRequestDTO scheduleDTO) {
        ScheduleDTO schedule = new ScheduleDTO();
        schedule.setTime(scheduleDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    // Additional service methods if needed
}
