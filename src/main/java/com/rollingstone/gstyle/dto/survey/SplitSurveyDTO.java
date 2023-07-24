package com.rollingstone.gstyle.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SplitSurveyDTO {
    private String menu_name;
    private String detail_rating;
    private String detail_reason;
}
