package com.rollingstone.gstyle.dao.event;

import com.rollingstone.gstyle.entity.Event;

import java.util.List;

public interface EventDAO {
    //Event findStudentByStuNo(Long edit_stu_no) throws Exception;

    Event updateEventCount(Long edit_stu_no) throws Exception;
    Boolean existsFindStudent(Long edit_stu_no);
    List<Event> listEventOrderByClass_CountDesc();
    void truncateEventTable();
}
