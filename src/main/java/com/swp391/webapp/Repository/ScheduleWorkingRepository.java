package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleWorkingRepository extends JpaRepository<Schedule, Integer> {
    // Additional custom queries if needed

    List<Schedule> findSchedulesByAccountAccountID(int accountId);
    //List<Schedule> findScheduleById(int id);

}

