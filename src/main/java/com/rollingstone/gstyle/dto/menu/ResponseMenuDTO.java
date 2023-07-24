package com.rollingstone.gstyle.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseMenuDTO {
    private String lunchDate;

    private List<String> menus;
}
