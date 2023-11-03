package com.edstem.project.contract.response;

import java.time.LocalDate;

public class NoteUnarchivedResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdDate;
    private boolean favorite;
    private boolean archive;
}
