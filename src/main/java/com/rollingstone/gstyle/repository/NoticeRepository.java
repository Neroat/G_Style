package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllNoticeByOrderByCreatedAtDesc();

    Notice findNoticeById(Long id);

}
