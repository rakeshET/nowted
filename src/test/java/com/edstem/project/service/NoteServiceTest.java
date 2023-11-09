package com.edstem.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.edstem.project.contract.request.NoteRequest;
import com.edstem.project.contract.response.NoteArchivedResponse;
import com.edstem.project.contract.response.NoteFavoriteResponse;
import com.edstem.project.contract.response.NoteInFolderResponse;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.NoteTrashedResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.model.Folder;
import com.edstem.project.model.Note;
import com.edstem.project.repository.FolderRepository;
import com.edstem.project.repository.NoteRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NoteService.class})
@ExtendWith(SpringExtension.class)
class NoteServiceTest {
    @MockBean private FolderRepository folderRepository;

    @MockBean private ModelMapper modelMapper;

    @MockBean private NoteRepository noteRepository;

    @Autowired private NoteService noteService;

    @Test
    void testCreateNote() {
        when(noteRepository.save(Mockito.<Note>any()))
                .thenThrow(new CustomException("An error occurred"));

        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());
        Optional<Folder> ofResult = Optional.of(folder);
        when(folderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Folder folder2 = new Folder();
        folder2.setId(1L);
        folder2.setName("Name");
        folder2.setNotes(new ArrayList<>());

        Note note = new Note();
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder2);
        note.setId(1L);
        note.setTitle("Dr");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Note>>any())).thenReturn(note);

        Folder folder3 = new Folder();
        folder3.setId(1L);
        folder3.setName("Name");
        folder3.setNotes(new ArrayList<>());

        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setFolder(folder3);
        assertThrows(CustomException.class, () -> noteService.createNote(noteRequest));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Note>>any());
        verify(folderRepository).findById(Mockito.<Long>any());
        verify(noteRepository).save(Mockito.<Note>any());
    }


    @Test
    void testGetAllNotes() {
        when(noteRepository.findAll()).thenReturn(new ArrayList<>());
        List<NoteResponse> actualAllNotes = noteService.getAllActiveNotes();
        verify(noteRepository).findAll();
        assertTrue(actualAllNotes.isEmpty());
    }

    @Test
    void testGetNoteById() {
        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());

        Note note = new Note();
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder);
        note.setId(1L);
        note.setTitle("Dr");
        Optional<Note> ofResult = Optional.of(note);
        when(noteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NoteResponse noteResponse = new NoteResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<NoteResponse>>any()))
                .thenReturn(noteResponse);
        NoteResponse actualNoteById = noteService.getNoteById(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<NoteResponse>>any());
        verify(noteRepository).findById(Mockito.<Long>any());
        assertSame(noteResponse, actualNoteById);
    }

    @Test
    void testUpdateNote() {
        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());

        Note note = new Note();
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder);
        note.setId(1L);
        note.setTitle("Dr");
        Optional<Note> ofResult = Optional.of(note);

        Folder folder2 = new Folder();
        folder2.setId(1L);
        folder2.setName("Name");
        folder2.setNotes(new ArrayList<>());

        Note note2 = new Note();
        note2.setArchive(true);
        note2.setContent("Not all who wander are lost");
        note2.setCreatedDate(LocalDate.of(1970, 1, 1));
        note2.setFavorite(true);
        note2.setFolder(folder2);
        note2.setId(1L);
        note2.setTitle("Dr");
        when(noteRepository.save(Mockito.<Note>any())).thenReturn(note2);
        when(noteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NoteResponse noteResponse = new NoteResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<NoteResponse>>any()))
                .thenReturn(noteResponse);
        NoteResponse actualUpdateNoteResult = noteService.updateNote(1L, new NoteRequest());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<NoteResponse>>any());
        verify(noteRepository).findById(Mockito.<Long>any());
        verify(noteRepository).save(Mockito.<Note>any());
        assertSame(noteResponse, actualUpdateNoteResult);
    }

    @Test
    void testGetAllNotesInFolder() {
        when(noteRepository.findByFolderId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        List<NoteInFolderResponse> actualAllNotesInFolder = noteService.getAllActiveNotesInFolder(1L);
        verify(noteRepository).findByFolderId(Mockito.<Long>any());
        assertTrue(actualAllNotesInFolder.isEmpty());
    }

    @Test
    void testGetFavoriteNotes() {
        when(noteRepository.findByFavoriteTrue()).thenReturn(new ArrayList<>());
        List<NoteFavoriteResponse> actualFavoriteNotes = noteService.getFavoriteNotes();
        verify(noteRepository).findByFavoriteTrue();
        assertTrue(actualFavoriteNotes.isEmpty());
    }

    @Test
    void testAddNoteToFavorites() {
        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());

        Note note = new Note();
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder);
        note.setId(1L);
        note.setTitle("Dr");
        Optional<Note> ofResult = Optional.of(note);

        Folder folder2 = new Folder();
        folder2.setId(1L);
        folder2.setName("Name");
        folder2.setNotes(new ArrayList<>());

        Note note2 = new Note();
        note2.setArchive(true);
        note2.setContent("Not all who wander are lost");
        note2.setCreatedDate(LocalDate.of(1970, 1, 1));
        note2.setFavorite(true);
        note2.setFolder(folder2);
        note2.setId(1L);
        note2.setTitle("Dr");
        when(noteRepository.save(Mockito.<Note>any())).thenReturn(note2);
        when(noteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NoteFavoriteResponse noteFavoriteResponse = new NoteFavoriteResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<NoteFavoriteResponse>>any()))
                .thenReturn(noteFavoriteResponse);
        NoteFavoriteResponse actualAddNoteToFavoritesResult = noteService.addNoteToFavorites(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<NoteFavoriteResponse>>any());
        verify(noteRepository).findById(Mockito.<Long>any());
        verify(noteRepository).save(Mockito.<Note>any());
        assertSame(noteFavoriteResponse, actualAddNoteToFavoritesResult);
    }

    @Test
    void testRemoveNoteFromFavorites() {
        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());

        Note note = new Note();
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder);
        note.setId(1L);
        note.setTitle("Dr");
        Optional<Note> ofResult = Optional.of(note);

        Folder folder2 = new Folder();
        folder2.setId(1L);
        folder2.setName("Name");
        folder2.setNotes(new ArrayList<>());

        Note note2 = new Note();
        note2.setArchive(true);
        note2.setContent("Not all who wander are lost");
        note2.setCreatedDate(LocalDate.of(1970, 1, 1));
        note2.setFavorite(true);
        note2.setFolder(folder2);
        note2.setId(1L);
        note2.setTitle("Dr");
        when(noteRepository.save(Mockito.<Note>any())).thenReturn(note2);
        when(noteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NoteFavoriteResponse noteFavoriteResponse = new NoteFavoriteResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<NoteFavoriteResponse>>any()))
                .thenReturn(noteFavoriteResponse);
        NoteFavoriteResponse actualRemoveNoteFromFavoritesResult =
                noteService.removeNoteFromFavorites(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<NoteFavoriteResponse>>any());
        verify(noteRepository).findById(Mockito.<Long>any());
        verify(noteRepository).save(Mockito.<Note>any());
        assertSame(noteFavoriteResponse, actualRemoveNoteFromFavoritesResult);
    }

    @Test
    void testGetArchivedNotes() {
        when(noteRepository.findByArchiveTrue()).thenReturn(new ArrayList<>());
        List<NoteArchivedResponse> actualArchivedNotes = noteService.getArchivedNotes();
        verify(noteRepository).findByArchiveTrue();
        assertTrue(actualArchivedNotes.isEmpty());
    }

    @Test
    void testArchiveNote() {
        Long id = 1L;
        Note note = new Note();
        note.setArchive(false);

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        when(noteRepository.save(note)).thenReturn(note);
        when(modelMapper.map(note, NoteArchivedResponse.class)).thenReturn(new NoteArchivedResponse());

        NoteArchivedResponse result = noteService.archiveNote(id);

        assertTrue(note.isArchive());
        assertNotNull(result);
        verify(noteRepository, times(1)).findById(id);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testArchiveNote_NotFound() {
        Long id = 1L;

        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        NoteArchivedResponse result = noteService.archiveNote(id);

        assertNull(result);
        verify(noteRepository, times(1)).findById(id);
    }
    @Test
    void testUnarchiveNote() {
        Long id = 1L;
        Note note = new Note();
        note.setArchive(true);

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        when(noteRepository.save(note)).thenReturn(note);
        when(modelMapper.map(note, NoteArchivedResponse.class)).thenReturn(new NoteArchivedResponse());

        NoteArchivedResponse result = noteService.unarchiveNote(id);

        assertFalse(note.isArchive());
        assertNotNull(result);
        verify(noteRepository, times(1)).findById(id);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testUnarchiveNote_NotFound() {
        Long id = 1L;

        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> {
            noteService.unarchiveNote(id);
        });

        String expectedMessage = "Note not found with ID: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(noteRepository, times(1)).findById(id);
    }
    @Test
    void testTrashNote() {
        Long id = 1L;
        Note note = new Note();
        note.setTrash(false);

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        when(noteRepository.save(note)).thenReturn(note);
        when(modelMapper.map(note, NoteTrashedResponse.class)).thenReturn(new NoteTrashedResponse());

        NoteTrashedResponse result = noteService.trashNote(id);

        assertTrue(note.isTrash());
        assertNotNull(result);
        verify(noteRepository, times(1)).findById(id);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testTrashNote_NotFound() {
        Long id = 1L;

        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        NoteTrashedResponse result = noteService.trashNote(id);

        assertNull(result);
        verify(noteRepository, times(1)).findById(id);
    }
    @Test
    void testGetTrashedNotes() {
        List<Note> trashedNotes = new ArrayList<>();
        Note note = new Note();
        note.setTrash(true);
        trashedNotes.add(note);

        when(noteRepository.findByTrashTrue()).thenReturn(trashedNotes);
        when(modelMapper.map(note, NoteTrashedResponse.class)).thenReturn(new NoteTrashedResponse());

        List<NoteTrashedResponse> result = noteService.getTrashedNotes();

        assertFalse(result.isEmpty());
        verify(noteRepository, times(1)).findByTrashTrue();
    }

    @Test
    void testRestoreNoteFromTrash() {
        Long id = 1L;
        Note note = new Note();
        note.setTrash(true);

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        when(noteRepository.save(note)).thenReturn(note);
        when(modelMapper.map(note, NoteTrashedResponse.class)).thenReturn(new NoteTrashedResponse());

        NoteTrashedResponse result = noteService.restoreNoteFromTrash(id);

        assertFalse(note.isTrash());
        assertNotNull(result);
        verify(noteRepository, times(1)).findById(id);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testRestoreNoteFromTrash_NotFound() {
        Long id = 1L;

        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> {
            noteService.restoreNoteFromTrash(id);
        });

        String expectedMessage = "Note not found with ID: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(noteRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteNote() {
        Long id = 1L;
        Note note = new Note();
        note.setTrash(true);

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        ResponseEntity<?> result = noteService.deleteNote(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(noteRepository, times(1)).findById(id);
        verify(noteRepository, times(1)).delete(note);
    }

    @Test
    void testDeleteNote_NotInTrash() {
        Long id = 1L;
        Note note = new Note();
        note.setTrash(false);

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        Exception exception = assertThrows(CustomException.class, () -> {
            noteService.deleteNote(id);
        });

        String expectedMessage = "Note with ID: " + id + " is not in the trash";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteNote_NotFound() {
        Long id = 1L;

        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> {
            noteService.deleteNote(id);
        });

        String expectedMessage = "Note not found with ID: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

