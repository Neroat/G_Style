package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.menu.ResponseMenuDTO;
import com.rollingstone.gstyle.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/today")
    public ResponseEntity todayMenus(String date) {
        ResponseMenuDTO responseMenuDTO = menuService.getMenus(date);
        return new ResponseEntity(responseMenuDTO, HttpStatus.OK);
    }

    @GetMapping("/period")
    public ResponseEntity periodMenus(String fromDate, String toDate) {
        List<ResponseMenuDTO> menuDTOList = menuService.getMenusPeriod(fromDate, toDate);
        return new ResponseEntity(menuDTOList, HttpStatus.OK);
    }

}
