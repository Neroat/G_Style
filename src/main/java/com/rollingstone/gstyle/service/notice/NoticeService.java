package com.rollingstone.gstyle.service.notice;

import com.rollingstone.gstyle.dto.notice.RequestNoticeDTO;
import com.rollingstone.gstyle.dto.notice.ResponseNoticeDTO;

import java.util.List;

public interface NoticeService {

    ResponseNoticeDTO updateNotice(Long id, String title, String description) throws Exception;

    ResponseNoticeDTO saveNotice(RequestNoticeDTO requestNoticeDTO);

    void deleteNotice(Long id) throws Exception;

    List<ResponseNoticeDTO> getListNoticeOrderByCreatedAtDesc();

    ResponseNoticeDTO getNoticeById(Long id);

    void truncateNoticeTable();
}
