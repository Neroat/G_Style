package com.rollingstone.gstyle.service.event;

import com.rollingstone.gstyle.dto.event.ResponseEventDTO;

import java.util.List;

public interface EventService {

    void countUp(Long stu_no) throws Exception;

    List<ResponseEventDTO> getListEventOrderByCountDesc();

    void truncateEventTable();

    void insertClass();
}
