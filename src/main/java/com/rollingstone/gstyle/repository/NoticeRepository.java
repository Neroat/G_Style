package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllNoticeByOrderByCreatedAtDesc();

    Notice findNoticeById(Long id);

    @Query(value = "truncate notice", nativeQuery = true)
    @Modifying
    @Transactional
    void truncateNoticeTable();

}
