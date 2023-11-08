package com.edstem.project.service;

import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.model.Note;
import com.edstem.project.model.NoteBackup;
import com.edstem.project.repository.NoteBackupRepository;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteBackupService {
    private final NoteBackupRepository noteBackupRepository;
    private final NoteService noteService;
    private final ModelMapper modelMapper;

    public List<NoteBackup> getBackupsForMonth(YearMonth month) {
        return noteBackupRepository.findByMonth(month);
    }

    public void createBackupForMonth(YearMonth month) {
        List<NoteResponse> notes = noteService.getAllActiveNotes();

        List<NoteResponse> notesToBackup =
                notes.stream()
                        .filter(
                                note ->
                                        note.getCreatedDate().getYear() == month.getYear()
                                                && note.getCreatedDate().getMonth()
                                                        == month.getMonth())
                        .toList();

        List<Note> noteEntities =
                notesToBackup.stream()
                        .map(note -> modelMapper.map(note, Note.class))
                        .collect(Collectors.toList());

        NoteBackup backup = NoteBackup.builder().month(month).notes(noteEntities).build();

        noteBackupRepository.save(backup);
    }
}
