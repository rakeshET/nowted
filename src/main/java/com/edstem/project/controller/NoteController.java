package com.edstem.project.controller;

import com.edstem.project.contract.request.NoteRequest;
import com.edstem.project.contract.response.NoteArchivedResponse;
import com.edstem.project.contract.response.NoteFavoriteResponse;
import com.edstem.project.contract.response.NoteInFolderResponse;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.NoteTrashedResponse;
import com.edstem.project.contract.response.SearchResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.service.NoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
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
        return noteService.getAllActiveNotes();
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

    @GetMapping("/folders/{folderId}/notes")
    public List<NoteInFolderResponse> getAllNotesInFolder(@PathVariable Long folderId) {
        return noteService.getAllActiveNotesInFolder(folderId);
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

    @GetMapping("/trashed")
    public List<NoteTrashedResponse> getTrashedNotes() {
        return noteService.getTrashedNotes();
    }

    @PutMapping("/{id}/trash")
    public NoteTrashedResponse trashNote(@PathVariable Long id) {
        return noteService.trashNote(id);
    }

    @DeleteMapping("/{id}/trash")
    public NoteTrashedResponse restoreNoteFromTrash(@PathVariable Long id) {
        return noteService.restoreNoteFromTrash(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }

    @GetMapping("/search")
    public List<SearchResponse> search(@RequestParam String query) {
        return noteService.searchByQuery(query);
    }
}
