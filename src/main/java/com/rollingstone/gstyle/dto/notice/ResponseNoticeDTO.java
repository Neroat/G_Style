package com.rollingstone.gstyle.dto.notice;

import com.rollingstone.gstyle.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNoticeDTO {
    private Long id;
    private String title;
    private String description;
    private String createdat;

    public ResponseNoticeDTO(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.description = notice.getDescription();
        this.createdat = notice.getCreatedAt();
    }
}
