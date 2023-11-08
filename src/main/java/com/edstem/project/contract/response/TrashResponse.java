package com.edstem.project.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrashResponse {
    private Long id;
    private NoteResponse note;
    private Long folderId;
    private String folderName;
}
