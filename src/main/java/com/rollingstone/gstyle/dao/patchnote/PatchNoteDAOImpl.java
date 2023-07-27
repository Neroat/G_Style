package com.rollingstone.gstyle.dao.patchnote;

import com.rollingstone.gstyle.entity.PatchNote;
import com.rollingstone.gstyle.repository.PatchNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatchNoteDAOImpl implements PatchNoteDAO{

    private final PatchNoteRepository patchNoteRepository;

    @Autowired
    public PatchNoteDAOImpl(PatchNoteRepository patchNoteRepository) {
        this.patchNoteRepository = patchNoteRepository;
    }

    @Override
    public PatchNote updatePatchNote(Long id, String title, String description) throws Exception {
        Optional<PatchNote> selectedPatchNote = patchNoteRepository.findById(id);

        PatchNote updatePatchNote;
        if(selectedPatchNote.isPresent()) {
            PatchNote patchNote = selectedPatchNote.get();
            patchNote.setTitle(title);
            patchNote.setDescription(description);

            updatePatchNote = patchNoteRepository.save(patchNote);
        }   else throw new Exception();

        return updatePatchNote;
    }

    @Override
    public PatchNote insertPatchNote(PatchNote patchNote) {
        PatchNote savePatchNote = patchNoteRepository.save(patchNote);
        return savePatchNote;
    }

    @Override
    public void deletePatchNote(Long id) throws Exception {
        Optional<PatchNote> selectedPatchNote = patchNoteRepository.findById(id);

        if(selectedPatchNote.isPresent()) {
            PatchNote patchNote = selectedPatchNote.get();
            patchNoteRepository.delete(patchNote);
        }   else throw new Exception();
    }

    @Override
    public List<PatchNote> listPatchNoteOrderByCreatedAtDesc() {
        return patchNoteRepository.findAllPatchNoteByOrderByCreatedAtDesc();
    }

    @Override
    public PatchNote selectedPatchNote(Long id) {
        return patchNoteRepository.findPatchNoteById(id);
    }

    @Override
    public void truncatePatchNoteTable() {
        patchNoteRepository.truncatePatchNoteTable();
    }
}
