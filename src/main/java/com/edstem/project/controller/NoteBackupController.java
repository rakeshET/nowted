package com.edstem.project.controller;

import com.edstem.project.model.NoteBackup;
import com.edstem.project.service.NoteBackupService;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/backup")
public class NoteBackupController {

    private final NoteBackupService noteBackupService;

    @GetMapping
    public List<NoteBackup> getBackupsForMonth(
            @RequestParam String year, @RequestParam String month) {
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), Month.valueOf(month));
        return noteBackupService.getBackupsForMonth(yearMonth);
    }

    @PostMapping
    public void createBackupForMonth(@RequestParam String year, @RequestParam String month) {
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), Month.valueOf(month));
        noteBackupService.createBackupForMonth(yearMonth);
    }
}
