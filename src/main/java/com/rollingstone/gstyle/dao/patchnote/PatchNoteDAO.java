package com.rollingstone.gstyle.dao.patchnote;


import com.rollingstone.gstyle.entity.PatchNote;

import java.util.List;

public interface PatchNoteDAO {
    PatchNote updatePatchNote(Long id, String title, String description) throws Exception;
    PatchNote insertPatchNote(PatchNote patchNote);
    void deletePatchNote(Long id) throws Exception;
    List<PatchNote> listPatchNoteOrderByCreatedAtDesc();
    PatchNote selectedPatchNote(Long id);

    void truncatePatchNoteTable();
}
