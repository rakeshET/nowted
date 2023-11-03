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
public class NoteFavoriteResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdDate;
}
