package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.event.ResponseEventDTO;
import com.rollingstone.gstyle.service.DefaultValidIdPwd;
import com.rollingstone.gstyle.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final DefaultValidIdPwd defaultValidIdPwd;

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService, DefaultValidIdPwd defaultValidIdPwd) {
        this.eventService = eventService;
        this.defaultValidIdPwd = defaultValidIdPwd;
    }

    @GetMapping("/rank")
    public ResponseEntity<List<ResponseEventDTO>> rank() {
        List<ResponseEventDTO> eventDTOList = eventService.getListEventOrderByCountDesc();
        List<ResponseEventDTO> newDTOList = eventDTOList.subList(0,3);
        return ResponseEntity.status(HttpStatus.OK).body(newDTOList);
    }

    @DeleteMapping("/init")
    public ResponseEntity<String> init(@RequestParam String id, @RequestParam String password) {
        if(defaultValidIdPwd.isRight(id, password)) {
            eventService.truncateEventTable();
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 초기화 됨.");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID와 PASSWORD가 잘못됨.");
    }

    @PutMapping("class")
    public ResponseEntity<String> insertClass(@RequestParam String id, @RequestParam String password) {
        if(defaultValidIdPwd.isRight(id, password)) {
            eventService.insertClass();
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 학년반이 입력됨.");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID와 PASSWORD가 잘못됨.");
    }
}
