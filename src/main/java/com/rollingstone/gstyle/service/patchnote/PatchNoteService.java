package com.rollingstone.gstyle.service.patchnote;

import com.rollingstone.gstyle.dto.patchnote.RequestPatchNoteDTO;
import com.rollingstone.gstyle.dto.patchnote.ResponsePatchNoteDTO;
import com.rollingstone.gstyle.dto.patchnote.UpdatePatchNoteDTO;

import java.util.List;

public interface PatchNoteService {

    ResponsePatchNoteDTO updatePatchNote(UpdatePatchNoteDTO updatePatchNoteDTO) throws Exception;

    ResponsePatchNoteDTO savePatchNote(RequestPatchNoteDTO requestNoticeDTO);

    void deleteNotice(Long id) throws Exception;

    List<ResponsePatchNoteDTO> getListPatchNoteOrderByCreatedAtDesc();

    ResponsePatchNoteDTO getPatchNoteById(Long id);

    void truncatePatchNoteTable();
}
