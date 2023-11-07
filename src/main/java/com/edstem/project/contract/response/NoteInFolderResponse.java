package com.edstem.project.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteInFolderResponse {
//    private Long id;
//    private String message;
private Long id;
    private String title;
    private String content;
    private LocalDate createdDate;
}
