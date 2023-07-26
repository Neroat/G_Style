package com.rollingstone.gstyle.service.patchnote;

import com.rollingstone.gstyle.dto.patchnote.RequestPatchNoteDTO;
import com.rollingstone.gstyle.dto.patchnote.ResponsePatchNoteDTO;

import java.util.List;

public interface PatchNoteService {

    ResponsePatchNoteDTO updatePatchNote(Long id, String title, String description) throws Exception;

    ResponsePatchNoteDTO savePatchNote(RequestPatchNoteDTO requestNoticeDTO);

    void deleteNotice(Long id) throws Exception;

    List<ResponsePatchNoteDTO> getListPatchNoteOrderByCreatedAtDesc();

    ResponsePatchNoteDTO getPatchNoteById(Long id);
}
