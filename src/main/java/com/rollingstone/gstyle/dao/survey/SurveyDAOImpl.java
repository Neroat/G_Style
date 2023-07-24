package com.rollingstone.gstyle.dao.survey;

import com.rollingstone.gstyle.entity.Survey;
import com.rollingstone.gstyle.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyDAOImpl implements SurveyDAO{

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyDAOImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey insertSurvey(Survey survey) {
        Survey saveSurvey = surveyRepository.save(survey);
        return saveSurvey;
    }

    @Override
    public List<Survey> listSurveyByCreatedAt(String date) {
        return surveyRepository.findSurveysByCreatedAt(date);
    }

    @Override
    public Boolean existsSurveyByStu_NoAndDate(Long stu_no, String date) {
        Boolean existsSurvey = surveyRepository.existsSurveyByStunoAndCreatedAt(stu_no, date);
        return existsSurvey;
    }

    @Override
    public List<Survey> listAllSurvey() {
        return surveyRepository.findAll();
    }
}
