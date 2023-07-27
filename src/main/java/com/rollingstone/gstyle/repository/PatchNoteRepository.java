package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.PatchNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatchNoteRepository extends JpaRepository<PatchNote, Long> {

    List<PatchNote> findAllPatchNoteByOrderByCreatedAtDesc();

    PatchNote findPatchNoteById(Long id);

    @Query(value = "truncate patch_note", nativeQuery = true)
    @Modifying
    @Transactional
    void truncatePatchNoteTable();
}
