package com.rollingstone.gstyle.dto.survey;

import com.rollingstone.gstyle.entity.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSurveyDTO {
    private Long id;
    private Long stuno;
    private String erating;
    private String createdAt;
    private List<SplitSurveyDTO> splitSurveyDTOList;

    public ResponseSurveyDTO(Survey survey) {
        this.id = survey.getId();
        this.stuno = survey.getStuno();
        this.erating = survey.getErating();
        this.createdAt = survey.getCreatedAt();
        this.splitSurveyDTOList = split(survey.getDetailSurvey());
    }

    public List<SplitSurveyDTO> split(String detail) {
        String [] splitSurveyOne = detail.split("\\|");
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
        return splitSurveyDTOList;
    }
}

