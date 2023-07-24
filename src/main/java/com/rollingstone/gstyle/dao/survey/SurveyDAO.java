package com.rollingstone.gstyle.dao.survey;

import com.rollingstone.gstyle.entity.Survey;

import java.util.List;

public interface SurveyDAO {
    Survey insertSurvey(Survey survey);

    List<Survey> listSurveyByCreatedAt(String date);

    Boolean existsSurveyByStu_NoAndDate(Long stu_no, String date);

    List<Survey> listAllSurvey();
}
