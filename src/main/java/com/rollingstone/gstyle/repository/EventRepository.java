package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllEventByOrderByClasscountDesc();

    Boolean existsEventByStuno(Long stu_no);

    Event findEventByStuno(Long stu_no);

    @Query(value = "truncate survey_event", nativeQuery = true)
    @Modifying
    @Transactional
    void truncateEventTable();
}
