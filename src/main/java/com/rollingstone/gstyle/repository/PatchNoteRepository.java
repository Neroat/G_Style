package com.rollingstone.gstyle.repository;

import com.rollingstone.gstyle.entity.PatchNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatchNoteRepository extends JpaRepository<PatchNote, Long> {

    List<PatchNote> findAllPatchNoteByOrderByCreatedAtDesc();

    PatchNote findPatchNoteById(Long id);
}
