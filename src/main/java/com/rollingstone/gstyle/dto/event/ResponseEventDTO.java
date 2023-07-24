package com.rollingstone.gstyle.dto.event;

import com.rollingstone.gstyle.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEventDTO {
    private Long stu_no;
    private int class_count;

    public ResponseEventDTO(Event event) {
        this.stu_no = event.getStuno();
        this.class_count = event.getClasscount();
    }
}
