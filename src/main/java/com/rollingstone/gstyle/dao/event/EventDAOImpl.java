package com.rollingstone.gstyle.dao.event;

import com.rollingstone.gstyle.entity.Event;
import com.rollingstone.gstyle.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDAOImpl implements EventDAO{

    private final EventRepository eventRepository;

    @Autowired
    public EventDAOImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event updateEventCount(Long edit_stu_no) throws Exception {
        Boolean existsStudentClass = eventRepository.existsEventByStuno(edit_stu_no);
        Event updateEvent;
        if(existsStudentClass) {
            Event event = eventRepository.findEventByStuno(edit_stu_no);
            event.setClasscount(event.getClasscount()+1);
            updateEvent = eventRepository.save(event);
        } else throw new Exception("해당 학년반이 존재하지 않음.");

        return updateEvent;
    }

    @Override
    public Boolean existsFindStudent(Long edit_stu_no) {
        return eventRepository.existsEventByStuno(edit_stu_no);
    }

    @Override
    public List<Event> listEventOrderByClass_CountDesc() {
        return eventRepository.findAllEventByOrderByClasscountDesc();
    }

    @Override
    public void truncateEventTable() {
        eventRepository.truncateEventTable();
    }
}
