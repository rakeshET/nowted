package com.edstem.project.contract.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchResponse {

    private String noteName;
    private String content;
    private String folderName;

    public SearchResponse() {

    }
}
