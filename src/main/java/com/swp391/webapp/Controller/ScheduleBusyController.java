package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.ScheduleBusyEntity;
import com.swp391.webapp.Service.ScheduleBusyService;
import com.swp391.webapp.dto.ScheduleBusyRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/schedulebusy")
public class ScheduleBusyController {

    @Autowired
    private ScheduleBusyService scheduleBusyService;

    //Xem lich ban
    @GetMapping("/getAllBusySchedule/{hostId}")
    public List<ScheduleBusyEntity> getAllBusySchedule(@PathVariable int hostId){
        List<ScheduleBusyEntity> listSchedule = scheduleBusyService.getAllBusyScheduleByHost(hostId);
        return listSchedule;
    }

    //Them lich ban
    @PostMapping("/addBusySchedule")
    public ScheduleBusyEntity addBusySchedule(@RequestBody ScheduleBusyRequestDTO scheduleBusyRequestDTO){
        ScheduleBusyEntity schedule = scheduleBusyService.addBusySchedule(scheduleBusyRequestDTO);
        return schedule;
    }

    //Sua lich ban
    @PutMapping("/updateBusySchedule/{busyscheduleId}")
    public ScheduleBusyEntity updateBusySchedule(@PathVariable int busyscheduleId, @RequestBody ScheduleBusyRequestDTO scheduleBusyRequestDTO){
        ScheduleBusyEntity schedule = scheduleBusyService.updateBusySchedule(busyscheduleId,scheduleBusyRequestDTO);
        return schedule;
    }

    //Xoa lich ban
    @DeleteMapping("/deleteBusySchedule/{busyscheduleId}")
    public void deleteBusySchedule(@PathVariable int busyscheduleId){
        scheduleBusyService.deleteBusySchedule(busyscheduleId);
    }
}
