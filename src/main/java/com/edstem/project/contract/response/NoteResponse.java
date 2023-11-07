package com.edstem.project.contract.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdDate;
    private boolean favorite;
    private boolean archive;
    private Long folderId;
    private String folderName;
}
