package com.edstem.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.edstem.project.contract.request.NoteRequest;
import com.edstem.project.contract.response.NoteArchivedResponse;
import com.edstem.project.contract.response.NoteFavoriteResponse;
import com.edstem.project.contract.response.NoteInFolderResponse;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.NoteTrashedResponse;
import com.edstem.project.contract.response.SearchResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.service.NoteService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NoteController.class})
@ExtendWith(SpringExtension.class)
class NoteControllerTest {
    @MockBean private ModelMapper modelMapper;

    @Autowired private NoteController noteController;

    @MockBean private NoteService noteService;

    @Test
    void testCreateNote() {
        NoteRequest noteRequest = new NoteRequest();
        when(noteService.createNote(noteRequest)).thenReturn(new NoteResponse());

        NoteResponse result = noteController.createNote(noteRequest);

        assertNotNull(result);
        verify(noteService, times(1)).createNote(noteRequest);
    }

    @Test
    void testGetAllNotes() {
        when(noteService.getAllActiveNotes()).thenReturn(new ArrayList<>());

        List<NoteResponse> result = noteController.getAllNotes();

        assertNotNull(result);
        verify(noteService, times(1)).getAllActiveNotes();
    }

    @Test
    void testGetNoteById() {
        Long id = 1L;
        NoteResponse expectedResponse = new NoteResponse();

        when(noteService.getNoteById(id)).thenReturn(expectedResponse);

        NoteResponse result = noteController.getNoteById(id);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(noteService, times(1)).getNoteById(id);
    }

    @Test
    void testGetNoteById_NotFound() {
        Long id = 1L;

        when(noteService.getNoteById(id)).thenReturn(null);

        Exception exception =
                assertThrows(
                        CustomException.class,
                        () -> {
                            noteController.getNoteById(id);
                        });

        String expectedMessage = "Note not found with ID: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateNote() {
        Long id = 1L;
        NoteRequest request = new NoteRequest();

        when(noteService.updateNote(id, request)).thenReturn(new NoteResponse());

        NoteResponse result = noteController.updateNote(id, request);

        assertNotNull(result);
        verify(noteService, times(1)).updateNote(id, request);
    }

    @Test
    void testUpdateNote_NotFound() {
        Long id = 1L;
        NoteRequest request = new NoteRequest();

        when(noteService.updateNote(id, request)).thenReturn(null);

        Exception exception =
                assertThrows(
                        CustomException.class,
                        () -> {
                            noteController.updateNote(id, request);
                        });

        String expectedMessage = "Note not found with ID: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllNotesInFolder() {
        Long folderId = 1L;

        when(noteService.getAllActiveNotesInFolder(folderId)).thenReturn(new ArrayList<>());

        List<NoteInFolderResponse> result = noteController.getAllNotesInFolder(folderId);

        assertNotNull(result);
        verify(noteService, times(1)).getAllActiveNotesInFolder(folderId);
    }

    @Test
    void testGetFavoriteNotes() {
        when(noteService.getFavoriteNotes()).thenReturn(new ArrayList<>());

        List<NoteFavoriteResponse> result = noteController.getFavoriteNotes();

        assertNotNull(result);
        verify(noteService, times(1)).getFavoriteNotes();
    }

    @Test
    void testAddNoteToFavorites() {
        Long id = 1L;

        when(noteService.addNoteToFavorites(id)).thenReturn(new NoteFavoriteResponse());

        NoteFavoriteResponse result = noteController.addNoteToFavorites(id);

        assertNotNull(result);
        verify(noteService, times(1)).addNoteToFavorites(id);
    }

    @Test
    void testRemoveNoteFromFavorites() {
        Long id = 1L;

        when(noteService.removeNoteFromFavorites(id)).thenReturn(new NoteFavoriteResponse());

        NoteFavoriteResponse result = noteController.removeNoteFromFavorites(id);

        assertNotNull(result);
        verify(noteService, times(1)).removeNoteFromFavorites(id);
    }

    @Test
    void testGetArchivedNotes() {
        when(noteService.getArchivedNotes()).thenReturn(new ArrayList<>());

        List<NoteArchivedResponse> result = noteController.getArchivedNotes();

        assertNotNull(result);
        verify(noteService, times(1)).getArchivedNotes();
    }

    @Test
    void testArchiveNote() {
        Long id = 1L;

        when(noteService.archiveNote(id)).thenReturn(new NoteArchivedResponse());

        NoteArchivedResponse result = noteController.archiveNote(id);

        assertNotNull(result);
        verify(noteService, times(1)).archiveNote(id);
    }

    @Test
    void testUnarchiveNote() {
        Long id = 1L;

        when(noteService.unarchiveNote(id)).thenReturn(new NoteArchivedResponse());

        NoteArchivedResponse result = noteController.unarchiveNote(id);

        assertNotNull(result);
        verify(noteService, times(1)).unarchiveNote(id);
    }

    @Test
    void testGetTrashedNotes() {
        when(noteService.getTrashedNotes()).thenReturn(new ArrayList<>());

        List<NoteTrashedResponse> result = noteController.getTrashedNotes();

        assertNotNull(result);
        verify(noteService, times(1)).getTrashedNotes();
    }

    @Test
    void testTrashNote() {
        Long id = 1L;

        when(noteService.trashNote(id)).thenReturn(new NoteTrashedResponse());

        NoteTrashedResponse result = noteController.trashNote(id);

        assertNotNull(result);
        verify(noteService, times(1)).trashNote(id);
    }

    @Test
    void testRestoreNoteFromTrash() {
        Long id = 1L;

        when(noteService.restoreNoteFromTrash(id)).thenReturn(new NoteTrashedResponse());

        NoteTrashedResponse result = noteController.restoreNoteFromTrash(id);

        assertNotNull(result);
        verify(noteService, times(1)).restoreNoteFromTrash(id);
    }

    @Test
    void testDeleteNote() {
        Long id = 1L;

        when(noteService.deleteNote(id)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> result = noteController.deleteNote(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(noteService, times(1)).deleteNote(id);
    }

    @Test
    public void testSearch() {
        String query = "test";
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setTitle("test title");
        searchResponse.setContent("test content");
        List<SearchResponse> expectedResponse = Arrays.asList(searchResponse);

        when(noteService.searchByQuery(query)).thenReturn(expectedResponse);

        List<SearchResponse> actualResponse = noteController.search(query);

        assertEquals(expectedResponse, actualResponse);
    }
}
