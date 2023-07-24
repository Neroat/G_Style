package com.rollingstone.gstyle.service.event;

import com.rollingstone.gstyle.dao.event.EventDAO;
import com.rollingstone.gstyle.dto.event.ResponseEventDTO;
import com.rollingstone.gstyle.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final EventDAO eventDAO;

    @Autowired
    public EventServiceImpl(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public void countUp(Long stu_no) throws Exception{
        String cutStu = stu_no.toString().substring(0,3);
        Long edit_stu_no = Long.parseLong(cutStu);
        System.out.println(cutStu);
        eventDAO.updateEventCount(edit_stu_no);
        //Event findStudent = eventDAO.findStudentByStuNo(edit_stu_no);

//        Boolean exitst = eventDAO.existsFindStudent(edit_stu_no);
//
//        Event updateEvent;
//
//        if(!exitst) {
//            Event event = eventDAO.findStudentByStuNo(edit_stu_no);
//            event.setClasscount(event.getClasscount()+1);
//            updateEvent = eventRepository.save(event);
//        }   else throw new Exception("해당 학번(학년,반) 이 존재하지 않습니다. ");
//
//        return updateEvent;

    }

    @Override
    public List<ResponseEventDTO> getListEventOrderByCountDesc() {
        List<Event> eventList = eventDAO.listEventOrderByClass_CountDesc();
        List<ResponseEventDTO> responseEventDTOList = eventList.stream().map(event ->
                new ResponseEventDTO(event)).collect(Collectors.toList());
        return responseEventDTOList;
    }
}
