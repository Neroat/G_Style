package com.rollingstone.gstyle.dto.patchnote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatchNoteDTO {
    private Long id;
    private String title;
    private String description;
}
