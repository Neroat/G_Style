package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

        Boolean existsSurveyByStunoAndCreatedAt(Long stu_no, String date);
        //Survey findSurveyByStu_noAndCreatedAt(Long stu_no, String createdAt);

        List<Survey> findSurveysByCreatedAt(String date);

}
