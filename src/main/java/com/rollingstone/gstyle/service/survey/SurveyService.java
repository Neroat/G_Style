package com.rollingstone.gstyle.service.survey;

import com.rollingstone.gstyle.dto.survey.RequestSurveyDTO;
import com.rollingstone.gstyle.dto.survey.ResponseSurveyDTO;

import java.util.List;

public interface SurveyService {
    ResponseSurveyDTO saveSurvey(RequestSurveyDTO requestSurveyDTO) throws Exception;

    List<ResponseSurveyDTO> getListSurveyByCreatedAt(String date);

    List<ResponseSurveyDTO> getListAllSurvey();
}
