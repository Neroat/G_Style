package com.rollingstone.gstyle.dao.notice;

import com.rollingstone.gstyle.entity.Notice;

import java.util.List;

public interface NoticeDAO {
    Notice updateNotice(Long id, String title, String description) throws Exception;
    Notice insertNotice(Notice notice);
    void deleteNotice(Long id) throws Exception;
    List<Notice> listNoticeOrderByCreatedAtDesc();
    Notice selectedNotice(Long id);

    void truncateNoticeTable();
}
