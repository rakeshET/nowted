package com.edstem.project.contract.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private String title;
    private String content;
    private String folderName;
    private LocalDate createdDate;

}
