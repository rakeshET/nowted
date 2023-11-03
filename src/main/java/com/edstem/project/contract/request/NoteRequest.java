package com.edstem.project.contract.request;

import com.edstem.project.model.Folder;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {
    private String title;
    private String content;
    private LocalDate createdDate;
    private Folder folder;
}
