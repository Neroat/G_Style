package com.rollingstone.gstyle.service.notice;

import com.rollingstone.gstyle.dto.notice.RequestNoticeDTO;
import com.rollingstone.gstyle.dto.notice.ResponseNoticeDTO;
import com.rollingstone.gstyle.dto.notice.UpdateNoticeDTO;

import java.util.List;

public interface NoticeService {

    ResponseNoticeDTO updateNotice(UpdateNoticeDTO updateNoticeDTO) throws Exception;

    ResponseNoticeDTO saveNotice(RequestNoticeDTO requestNoticeDTO);

    void deleteNotice(Long id) throws Exception;

    List<ResponseNoticeDTO> getListNoticeOrderByCreatedAtDesc();

    ResponseNoticeDTO getNoticeById(Long id);

    void truncateNoticeTable();
}
