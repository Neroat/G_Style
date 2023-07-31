package com.rollingstone.gstyle.service.notice;

import com.rollingstone.gstyle.dao.notice.NoticeDAO;
import com.rollingstone.gstyle.dto.notice.RequestNoticeDTO;
import com.rollingstone.gstyle.dto.notice.ResponseNoticeDTO;
import com.rollingstone.gstyle.dto.notice.UpdateNoticeDTO;
import com.rollingstone.gstyle.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeDAO noticeDAO;

    @Autowired
    public NoticeServiceImpl(NoticeDAO noticeDAO) {
        this.noticeDAO = noticeDAO;
    }

    @Override
    public ResponseNoticeDTO updateNotice(UpdateNoticeDTO updateNoticeDTO) throws Exception {
        Notice changeNotice = noticeDAO.updateNotice(updateNoticeDTO.getId(), updateNoticeDTO.getTitle(), updateNoticeDTO.getDescription());
        return new ResponseNoticeDTO(changeNotice);
    }

    @Override
    public ResponseNoticeDTO saveNotice(RequestNoticeDTO requestNoticeDTO) {
        Notice notice = new Notice();
        notice.setTitle(requestNoticeDTO.getTitle());
        notice.setDescription(requestNoticeDTO.getDescription());
        notice.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

        Notice saveNotice = noticeDAO.insertNotice(notice);

        ResponseNoticeDTO responseNoticeDTO = new ResponseNoticeDTO();
        responseNoticeDTO.setId(saveNotice.getId());
        responseNoticeDTO.setTitle(saveNotice.getTitle());
        responseNoticeDTO.setDescription(saveNotice.getDescription());
        responseNoticeDTO.setCreatedat(saveNotice.getCreatedAt());
        return responseNoticeDTO;
    }

    @Override
    public void deleteNotice(Long id) throws Exception {
        noticeDAO.deleteNotice(id);
    }

    @Override
    public List<ResponseNoticeDTO> getListNoticeOrderByCreatedAtDesc() {
        List<Notice> noticeList = noticeDAO.listNoticeOrderByCreatedAtDesc();
        List<ResponseNoticeDTO> responseNoticeDTOList = noticeList.stream().map(notice ->
                new ResponseNoticeDTO(notice)).collect(Collectors.toList());
        return responseNoticeDTOList;
    }

    @Override
    public ResponseNoticeDTO getNoticeById(Long id) {
        Notice selectNotice = noticeDAO.selectedNotice(id);
        return new ResponseNoticeDTO(selectNotice);
    }

    @Override
    public void truncateNoticeTable() {
        noticeDAO.truncateNoticeTable();
    }
}
