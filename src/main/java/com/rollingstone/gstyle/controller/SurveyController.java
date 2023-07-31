package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.survey.RequestSurveyDTO;
import com.rollingstone.gstyle.dto.survey.ResponseSurveyDTO;
import com.rollingstone.gstyle.service.DefaultValidIdPwd;
import com.rollingstone.gstyle.service.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    private final DefaultValidIdPwd defaultValidIdPwd;
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService, DefaultValidIdPwd defaultValidIdPwd) {
        this.surveyService = surveyService;
        this.defaultValidIdPwd = defaultValidIdPwd;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseSurveyDTO>> list(@RequestParam String date) {
        List<ResponseSurveyDTO> surveyDTOList = surveyService.getListSurveyByCreatedAt(date);
        return ResponseEntity.status(HttpStatus.OK).body(surveyDTOList);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseSurveyDTO> insert(@RequestBody RequestSurveyDTO requestSurveyDTO) throws Exception{
        ResponseSurveyDTO result = surveyService.saveSurvey(requestSurveyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseSurveyDTO>> all() {
        List<ResponseSurveyDTO> surveyDTOList = surveyService.getListAllSurvey();
        return ResponseEntity.status(HttpStatus.OK).body(surveyDTOList);
    }

    @DeleteMapping("/init")
    public ResponseEntity<String> init() {
            surveyService.truncateSurveyTable();
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 초기화 됨.");
    }

//    @DeleteMapping("/init")
//    public ResponseEntity<String> init(@RequestParam String id, @RequestParam String password) {
//        if(defaultValidIdPwd.isRight(id, password)) {
//            surveyService.truncateSurveyTable();
//            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 초기화 됨.");
//        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID와 PASSWORD가 잘못됨.");
//    }
}
