package com.rollingstone.gstyle.dto.patchnote;

import com.rollingstone.gstyle.entity.PatchNote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePatchNoteDTO {
    private Long id;
    private String title;
    private String description;
    private String createdat;

    public ResponsePatchNoteDTO(PatchNote patchNote) {
        this.id = patchNote.getId();
        this.title = patchNote.getTitle();
        this.description = patchNote.getDescription();
        this.createdat = patchNote.getCreatedAt();
    }
}
