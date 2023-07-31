package com.rollingstone.gstyle.controller;

import com.rollingstone.gstyle.dto.notice.RequestNoticeDTO;
import com.rollingstone.gstyle.dto.notice.ResponseNoticeDTO;
import com.rollingstone.gstyle.service.DefaultValidIdPwd;
import com.rollingstone.gstyle.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    private final DefaultValidIdPwd defaultValidIdPwd;
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService, DefaultValidIdPwd defaultValidIdPwd) {
        this.noticeService = noticeService;
        this.defaultValidIdPwd = defaultValidIdPwd;
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseNoticeDTO> detailNotice(@RequestParam Long id) {
        ResponseNoticeDTO result = noticeService.getNoticeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseNoticeDTO>> listNotice() {
        List<ResponseNoticeDTO> noticeDTOList = noticeService.getListNoticeOrderByCreatedAtDesc();
        return ResponseEntity.status(HttpStatus.OK).body(noticeDTOList);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseNoticeDTO> insertNotice(@RequestBody RequestNoticeDTO requestNoticeDTO) {
        ResponseNoticeDTO result = noticeService.saveNotice(requestNoticeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseNoticeDTO> updateNotice(@RequestParam Long id, @RequestParam String title, @RequestParam String description) throws Exception{
        ResponseNoticeDTO result = noticeService.updateNotice(id, title, description);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) throws Exception{
        noticeService.deleteNotice(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제됨.");
    }

    @DeleteMapping("/init")
    public ResponseEntity<String> init() {
            noticeService.truncateNoticeTable();
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 초기화 됨.");
    }

//    @DeleteMapping("/init")
//    public ResponseEntity<String> init(@RequestParam String id, @RequestParam String password) {
//        if(defaultValidIdPwd.isRight(id, password)) {
//            noticeService.truncateNoticeTable();
//            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 초기화 됨.");
//        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID와 PASSWORD가 잘못됨.");
//    }
}
