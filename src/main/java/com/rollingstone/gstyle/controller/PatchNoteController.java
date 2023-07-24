package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.patchnote.RequestPatchNoteDTO;
import com.rollingstone.gstyle.dto.patchnote.ResponsePatchNoteDTO;
import com.rollingstone.gstyle.service.patchnote.PatchNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patchnote")
public class PatchNoteController {

    private final PatchNoteService patchNoteService;

    @Autowired
    public PatchNoteController(PatchNoteService patchNoteService) {
        this.patchNoteService = patchNoteService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponsePatchNoteDTO>> list() {
        List<ResponsePatchNoteDTO> patchNoteDTOList = patchNoteService.getListPatchNoteOrderByCreatedAtDesc();
        return ResponseEntity.status(HttpStatus.OK).body(patchNoteDTOList);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponsePatchNoteDTO> insert(@RequestBody RequestPatchNoteDTO requestPatchNoteDTO) {
        ResponsePatchNoteDTO responsePatchNoteDTO = patchNoteService.savePatchNote(requestPatchNoteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responsePatchNoteDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponsePatchNoteDTO> update(@RequestParam Long id, @RequestParam String title, @RequestParam String description) throws Exception {
        ResponsePatchNoteDTO responsePatchNoteDTO = patchNoteService.updatePatchNote(id, title, description);
        return ResponseEntity.status(HttpStatus.OK).body(responsePatchNoteDTO);
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponsePatchNoteDTO> detail(@RequestParam Long id) {
        ResponsePatchNoteDTO responsePatchNoteDTO = patchNoteService.getPatchNoteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responsePatchNoteDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) throws Exception {
        patchNoteService.deleteNotice(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제됨.");
    }

}
