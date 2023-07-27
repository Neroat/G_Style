package com.rollingstone.gstyle.service.survey;

import com.rollingstone.gstyle.dao.event.EventDAO;
import com.rollingstone.gstyle.dao.survey.SurveyDAO;
import com.rollingstone.gstyle.dto.survey.SplitSurveyDTO;
import com.rollingstone.gstyle.dto.survey.RequestSurveyDTO;
import com.rollingstone.gstyle.dto.survey.ResponseSurveyDTO;
import com.rollingstone.gstyle.entity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService{

    private final SurveyDAO surveyDAO;
    private final EventDAO eventDAO;

    @Autowired
    public SurveyServiceImpl(SurveyDAO surveyDAO, EventDAO eventDAO) {
        this.surveyDAO = surveyDAO;
        this.eventDAO = eventDAO;
    }

    @Override
    public ResponseSurveyDTO saveSurvey(RequestSurveyDTO requestSurveyDTO) throws Exception {
        String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        Boolean exist = surveyDAO.existsSurveyByStu_NoAndDate(requestSurveyDTO.getStuno(), nowTime);
        System.out.println("설문조사 응답여부 exists = " + exist);
        if(!exist) {
            Survey survey = new Survey();
            survey.setStuno(requestSurveyDTO.getStuno());
            survey.setErating(requestSurveyDTO.getErating());
            survey.setCreatedAt(nowTime);
            survey.setDetailSurvey(requestSurveyDTO.getDetailSurvey());

            Survey saveSurvey = surveyDAO.insertSurvey(survey);
            Long edit_stuNo = editStuNo(saveSurvey.getStuno());
            System.out.println(edit_stuNo);
            eventDAO.updateEventCount(edit_stuNo);

            ResponseSurveyDTO responseSurveyDTO = new ResponseSurveyDTO();
            responseSurveyDTO.setId(saveSurvey.getId());
            responseSurveyDTO.setStuno(saveSurvey.getStuno());
            responseSurveyDTO.setErating(saveSurvey.getErating());
            responseSurveyDTO.setCreatedAt(saveSurvey.getCreatedAt());
            //List<String> splitSurveyOne = Arrays.asList(requestSurveyDTO.getDetailSurvey().split("|"));
            System.out.println(saveSurvey.getDetailSurvey());
            String [] splitSurveyOne = saveSurvey.getDetailSurvey().split("\\|");
            for(int i=0; i< splitSurveyOne.length; i++) {
                System.out.println(splitSurveyOne[i]+ " 다음칸 ");
            }

            List<SplitSurveyDTO> splitSurveyDTOList = new ArrayList<>();
            for(int i=0; i<splitSurveyOne.length; i++) {
                String[] splitSurveyTwo = splitSurveyOne[i].split("\\_");
                SplitSurveyDTO splitSurveyDTO = new SplitSurveyDTO();
                splitSurveyDTO.setMenu_name(splitSurveyTwo[0]);
                splitSurveyDTO.setDetail_rating(splitSurveyTwo[1]);
                splitSurveyDTO.setDetail_reason(splitSurveyTwo[2]);
                splitSurveyDTOList.add(splitSurveyDTO);
            }
            responseSurveyDTO.setSplitSurveyDTOList(splitSurveyDTOList);
            System.out.println(responseSurveyDTO);
            return responseSurveyDTO;

        }   else throw new Exception("설문조사 응답여부 및 등록 Exception");
    }

    @Override
    public List<ResponseSurveyDTO> getListSurveyByCreatedAt(String date) {
        List<Survey> surveyList = surveyDAO.listSurveyByCreatedAt(date);
        List<ResponseSurveyDTO> surveyDTOList = surveyList.stream().map(survey ->
                new ResponseSurveyDTO(survey)).collect(Collectors.toList());
        return surveyDTOList;
    }

    @Override
    public List<ResponseSurveyDTO> getListAllSurvey() {
        List<Survey> surveyList = surveyDAO.listAllSurvey();
        List<ResponseSurveyDTO> surveyDTOList = surveyList.stream().map(survey ->
                new ResponseSurveyDTO(survey)).collect(Collectors.toList());
        return surveyDTOList;
    }

    public Long editStuNo(Long stu_no) {
        String cutStu = stu_no.toString().substring(0,3);
        Long edit_stu_no = Long.parseLong(cutStu);
        return edit_stu_no;
    }

    @Override
    public void truncateSurveyTable() {
        surveyDAO.truncateSurveyTable();
    }
}
