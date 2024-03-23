package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.ScheduleBusyEntity;
import com.swp391.webapp.Repository.ScheduleBusyRepository;
import com.swp391.webapp.dto.ScheduleBusyRequestDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleBusyService {

    @Autowired
    private ScheduleBusyRepository scheduleBusyRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountUtils accountUtils;

    public List<ScheduleBusyEntity> getAllBusyScheduleByHost(int hostId) {
        AccountEntity hostAccount = accountService.getAccountById(hostId).get();
        System.out.println(hostAccount.getName());
        List<ScheduleBusyEntity> scheduleBusyEntityList = scheduleBusyRepository.findAllScheduleBusyEntitiesByAccount(hostAccount);
        return scheduleBusyEntityList;
    }

    public ScheduleBusyEntity addBusySchedule(ScheduleBusyRequestDTO scheduleBusyRequestDTO) {
        AccountEntity hostAccount = accountUtils.getCurrentAccount();
        //Kiem tra trung lich
//        List<ScheduleBusyEntity> scheduleBusyEntityList = scheduleBusyRepository.findAll();
//        for (int i = 0; i < scheduleBusyEntityList.size(); i++) {
//            if (scheduleBusyEntityList.get(i).getDate().compareTo(scheduleBusyRequestDTO.getDate()) == 1) {
//                throw new RuntimeException("Ngay nay da bi trung roi");
//            }
//        }
        ScheduleBusyEntity scheduleBusyEntity = new ScheduleBusyEntity();
        scheduleBusyEntity.setDate(scheduleBusyRequestDTO.getDate());
        scheduleBusyEntity.setAccount(hostAccount);
        scheduleBusyEntity.setBusy(true);
        return scheduleBusyRepository.save(scheduleBusyEntity);
    }

    public ScheduleBusyEntity updateBusySchedule(int id, ScheduleBusyRequestDTO scheduleBusyRequestDTO) {
        ScheduleBusyEntity current = scheduleBusyRepository.findById(id).get();

        return scheduleBusyRepository.save(current);
    }

    public void deleteBusySchedule(int busyscheduleId) {
        scheduleBusyRepository.deleteById(busyscheduleId);
    }
}
