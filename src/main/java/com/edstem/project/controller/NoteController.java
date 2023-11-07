package com.edstem.project.controller;

import com.edstem.project.contract.request.NoteRequest;
import com.edstem.project.contract.response.NoteArchivedResponse;
import com.edstem.project.contract.response.NoteFavoriteResponse;
import com.edstem.project.contract.response.NoteInFolderResponse;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.service.NoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notes")
public class NoteController {
    private final NoteService noteService;
    private final ModelMapper modelMapper;

    @PostMapping
    public NoteResponse createNote(@RequestBody NoteRequest noteRequest) {
        return noteService.createNote(noteRequest);
    }

    @GetMapping
    public List<NoteResponse> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(@PathVariable Long id) {
        NoteResponse note = noteService.getNoteById(id);
        if (note == null) {
            throw new CustomException("Note not found with ID: " + id);
        }
        return note;
    }

    @PutMapping("/{id}")
    public NoteResponse updateNote(@PathVariable Long id, @RequestBody NoteRequest request) {
        NoteResponse note = noteService.updateNote(id, request);
        if (note == null) {
            throw new CustomException("Note not found with ID: " + id);
        }
        return note;
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

    @GetMapping("/folders/{folderId}/notes")
    public List<NoteInFolderResponse> getAllNotesInFolder(@PathVariable Long folderId) {
        return noteService.getAllNotesInFolder(folderId);
    }

    @GetMapping("/favorites")
    public List<NoteFavoriteResponse> getFavoriteNotes() {
        return noteService.getFavoriteNotes();
    }

    @PutMapping("/{id}/favorite")
    public NoteFavoriteResponse addNoteToFavorites(@PathVariable Long id) {
        return noteService.addNoteToFavorites(id);
    }

    @DeleteMapping("/{id}/favorite")
    public NoteFavoriteResponse removeNoteFromFavorites(@PathVariable Long id) {
        return noteService.removeNoteFromFavorites(id);
    }

    @GetMapping("/archived")
    public List<NoteArchivedResponse> getArchivedNotes() {
        return noteService.getArchivedNotes();
    }

    @PutMapping("/{id}/archive")
    public NoteArchivedResponse archiveNote(@PathVariable Long id) {
        return noteService.archiveNote(id);
    }

    @DeleteMapping("/{id}/archive")
    public NoteArchivedResponse unarchiveNote(@PathVariable Long id) {
        return noteService.unarchiveNote(id);
    }
}
