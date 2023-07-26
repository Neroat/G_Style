package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllEventByOrderByClasscountDesc();
    //Optional<Event> findEventByStu_no(Long stu_no);
    Boolean existsEventByStuno(Long stu_no);

    Event findEventByStuno(Long stu_no);
}
