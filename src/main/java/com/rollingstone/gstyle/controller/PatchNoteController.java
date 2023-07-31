package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.patchnote.RequestPatchNoteDTO;
import com.rollingstone.gstyle.dto.patchnote.ResponsePatchNoteDTO;
import com.rollingstone.gstyle.service.DefaultValidIdPwd;
import com.rollingstone.gstyle.service.patchnote.PatchNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patchnote")
public class PatchNoteController {

    private final DefaultValidIdPwd defaultValidIdPwd;
    private final PatchNoteService patchNoteService;

    @Autowired
    public PatchNoteController(PatchNoteService patchNoteService, DefaultValidIdPwd defaultValidIdPwd) {
        this.patchNoteService = patchNoteService;
        this.defaultValidIdPwd = defaultValidIdPwd;
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
    public ResponseEntity<String> delete(@RequestParam Long survey_id, @RequestParam String id, @RequestParam String password) throws Exception {
        if(defaultValidIdPwd.isRight(id, password)) {
            patchNoteService.deleteNotice(survey_id);
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제됨.");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID와 PASSWORD가 잘못됨.");

    }

    @DeleteMapping("/init")
    public ResponseEntity<String> init(@RequestParam String id, @RequestParam String password) {
        if(defaultValidIdPwd.isRight(id, password)) {
            patchNoteService.truncatePatchNoteTable();
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 초기화 됨.");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID와 PASSWORD가 잘못됨.");
    }

}
