package com.rollingstone.gstyle.dao.notice;

import com.rollingstone.gstyle.entity.Notice;
import com.rollingstone.gstyle.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NoticeDAOImpl implements NoticeDAO{

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeDAOImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Notice updateNotice(Long id, String title, String description) throws Exception {
        Optional<Notice> selectedNotice = noticeRepository.findById(id);

        Notice updateNotice;
        if(selectedNotice.isPresent()) {
            Notice notice = selectedNotice.get();
            notice.setTitle(title);
            notice.setDescription(description);

            updateNotice = noticeRepository.save(notice);
        }   else throw new Exception();

        return updateNotice;
    }

    @Override
    public Notice insertNotice(Notice notice) {
        Notice saveNotice = noticeRepository.save(notice);
        return saveNotice;
    }

    @Override
    public void deleteNotice(Long id) throws Exception {
        Optional<Notice> selectedNotice = noticeRepository.findById(id);

        if(selectedNotice.isPresent()) {
            Notice notice = selectedNotice.get();
            noticeRepository.delete(notice);
        }   else throw new Exception();
    }

    @Override
    public List<Notice> listNoticeOrderByCreatedAtDesc() {
        return noticeRepository.findAllNoticeByOrderByCreatedAtDesc();
    }

    @Override
    public Notice selectedNotice(Long id) {
        return noticeRepository.findNoticeById(id);
    }

    @Override
    public void truncateNoticeTable() {
        noticeRepository.truncateNoticeTable();
    }
}
