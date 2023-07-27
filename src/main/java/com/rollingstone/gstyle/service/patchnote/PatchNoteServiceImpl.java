package com.rollingstone.gstyle.service.patchnote;

import com.rollingstone.gstyle.dao.patchnote.PatchNoteDAO;
import com.rollingstone.gstyle.dto.patchnote.RequestPatchNoteDTO;
import com.rollingstone.gstyle.dto.patchnote.ResponsePatchNoteDTO;
import com.rollingstone.gstyle.entity.PatchNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatchNoteServiceImpl implements PatchNoteService {

    private final PatchNoteDAO patchNoteDAO;

    @Autowired
    public PatchNoteServiceImpl(PatchNoteDAO patchNoteDAO) {
        this.patchNoteDAO = patchNoteDAO;
    }

    @Override
    public ResponsePatchNoteDTO updatePatchNote(Long id, String title, String description) throws Exception {
        PatchNote changePatchNote = patchNoteDAO.updatePatchNote(id, title, description);
        return new ResponsePatchNoteDTO(changePatchNote);
    }

    @Override
    public ResponsePatchNoteDTO savePatchNote(RequestPatchNoteDTO requestPatchNoteDTO) {
        PatchNote patchNote = new PatchNote();
        patchNote.setTitle(requestPatchNoteDTO.getTitle());
        patchNote.setDescription(requestPatchNoteDTO.getDescription());
        patchNote.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

        PatchNote savePatchNote = patchNoteDAO.insertPatchNote(patchNote);

        ResponsePatchNoteDTO responsePatchNoteDTO = new ResponsePatchNoteDTO();
        responsePatchNoteDTO.setId(savePatchNote.getId());
        responsePatchNoteDTO.setTitle(savePatchNote.getTitle());
        responsePatchNoteDTO.setDescription(savePatchNote.getDescription());
        responsePatchNoteDTO.setCreatedat(savePatchNote.getCreatedAt());
        return responsePatchNoteDTO;
    }

    @Override
    public void deleteNotice(Long id) throws Exception {
        patchNoteDAO.deletePatchNote(id);
    }

    @Override
    public List<ResponsePatchNoteDTO> getListPatchNoteOrderByCreatedAtDesc() {
        List<PatchNote> patchNoteList = patchNoteDAO.listPatchNoteOrderByCreatedAtDesc();
        List<ResponsePatchNoteDTO> responsePatchNoteDTOList = patchNoteList.stream().map(patchNote ->
                new ResponsePatchNoteDTO(patchNote)).collect(Collectors.toList());
        return responsePatchNoteDTOList;
    }

    @Override
    public ResponsePatchNoteDTO getPatchNoteById(Long id) {
        PatchNote selectPatchNote = patchNoteDAO.selectedPatchNote(id);
        return new ResponsePatchNoteDTO(selectPatchNote);
    }

    @Override
    public void truncatePatchNoteTable() {
        patchNoteDAO.truncatePatchNoteTable();
    }
}
