package com.rollingstone.gstyle.service.event;

import com.rollingstone.gstyle.dao.event.EventDAO;
import com.rollingstone.gstyle.dto.event.ResponseEventDTO;
import com.rollingstone.gstyle.entity.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @PersistenceContext
    private EntityManager entityManager;
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

    @Override
    public void truncateEventTable() {
        eventDAO.truncateEventTable();
    }

    @Override
    @Transactional
    public void insertClass() {
        String sql =
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('101', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('102', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('103', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('104', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('106', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('107', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('108', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('109', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('110', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('111', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('112', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('113', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('114', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('201', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('202', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('203', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('204', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('205', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('206', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('207', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('208', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('209', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('210', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('211', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('212', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('213', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('214', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('301', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('302', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('303', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('304', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('305', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('306', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('307', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('308', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('309', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('310', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('311', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('312', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('313', '0'); " +
                "INSERT INTO SURVEY_EVENT (STUNO, CLASSCOUNT) VALUES('314', '0');";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
