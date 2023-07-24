package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.event.ResponseEventDTO;
import com.rollingstone.gstyle.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/rank")
    public ResponseEntity<List<ResponseEventDTO>> rank() {
        List<ResponseEventDTO> eventDTOList = eventService.getListEventOrderByCountDesc();
        List<ResponseEventDTO> newDTOList = eventDTOList.subList(0,3);
        return ResponseEntity.status(HttpStatus.OK).body(newDTOList);
    }

}
