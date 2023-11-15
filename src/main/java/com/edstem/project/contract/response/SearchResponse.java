package com.edstem.project.contract.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private String title;
    private String content;
    private String folderName;
    private LocalDate createdDate;
    private boolean archive;
    private boolean trash;
}
