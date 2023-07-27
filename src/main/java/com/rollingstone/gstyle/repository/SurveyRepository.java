package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

        Boolean existsSurveyByStunoAndCreatedAt(Long stu_no, String date);

        List<Survey> findSurveysByCreatedAt(String date);

        @Query(value = "truncate survey", nativeQuery = true)
        @Modifying
        @Transactional
        void truncateSurveyTable();
}
