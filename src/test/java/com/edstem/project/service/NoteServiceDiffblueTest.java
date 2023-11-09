package com.edstem.project.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.edstem.project.contract.response.SearchResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.model.Folder;
import com.edstem.project.model.Note;
import com.edstem.project.repository.FolderRepository;
import com.edstem.project.repository.NoteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NoteService.class})
@ExtendWith(SpringExtension.class)
class NoteServiceDiffblueTest {
    @MockBean
    private FolderRepository folderRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    /**
     * Method under test: {@link NoteService#searchByQuery(String)}
     */
    @Test
    void testSearchByQuery() {
        when(noteRepository.findAll()).thenReturn(new ArrayList<>());
        List<SearchResponse> actualSearchByQueryResult = noteService.searchByQuery("Query");
        verify(noteRepository).findAll();
        assertTrue(actualSearchByQueryResult.isEmpty());
    }

    /**
     * Method under test: {@link NoteService#searchByQuery(String)}
     */
    @Test
    void testSearchByQuery2() {
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
        note.setTrash(true);

        ArrayList<Note> noteList = new ArrayList<>();
        noteList.add(note);
        when(noteRepository.findAll()).thenReturn(noteList);
        List<SearchResponse> actualSearchByQueryResult = noteService.searchByQuery("Query");
        verify(noteRepository).findAll();
        assertTrue(actualSearchByQueryResult.isEmpty());
    }

    /**
     * Method under test: {@link NoteService#searchByQuery(String)}
     */
    @Test
    void testSearchByQuery3() {
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
        note.setTrash(true);

        Folder folder2 = new Folder();
        folder2.setId(2L);
        folder2.setName("42");
        folder2.setNotes(new ArrayList<>());

        Note note2 = new Note();
        note2.setArchive(false);
        note2.setContent("Content");
        note2.setCreatedDate(LocalDate.of(1970, 1, 1));
        note2.setFavorite(false);
        note2.setFolder(folder2);
        note2.setId(2L);
        note2.setTitle("Mr");
        note2.setTrash(false);

        ArrayList<Note> noteList = new ArrayList<>();
        noteList.add(note2);
        noteList.add(note);
        when(noteRepository.findAll()).thenReturn(noteList);
        List<SearchResponse> actualSearchByQueryResult = noteService.searchByQuery("Query");
        verify(noteRepository).findAll();
        assertTrue(actualSearchByQueryResult.isEmpty());
    }

    /**
     * Method under test: {@link NoteService#searchByQuery(String)}
     */
    @Test
    void testSearchByQuery4() {
        when(noteRepository.findAll()).thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> noteService.searchByQuery("Query"));
        verify(noteRepository).findAll();
    }

    /**
     * Method under test: {@link NoteService#searchByQuery(String)}
     */
    @Test
    void testSearchByQuery5() {
        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());
        Note note = mock(Note.class);
        when(note.isArchive()).thenReturn(true);
        doNothing().when(note).setArchive(anyBoolean());
        doNothing().when(note).setContent(Mockito.<String>any());
        doNothing().when(note).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(note).setFavorite(anyBoolean());
        doNothing().when(note).setFolder(Mockito.<Folder>any());
        doNothing().when(note).setId(Mockito.<Long>any());
        doNothing().when(note).setTitle(Mockito.<String>any());
        doNothing().when(note).setTrash(anyBoolean());
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder);
        note.setId(1L);
        note.setTitle("Dr");
        note.setTrash(true);

        ArrayList<Note> noteList = new ArrayList<>();
        noteList.add(note);
        when(noteRepository.findAll()).thenReturn(noteList);
        List<SearchResponse> actualSearchByQueryResult = noteService.searchByQuery("Query");
        verify(note).isArchive();
        verify(note).setArchive(anyBoolean());
        verify(note).setContent(Mockito.<String>any());
        verify(note).setCreatedDate(Mockito.<LocalDate>any());
        verify(note).setFavorite(anyBoolean());
        verify(note).setFolder(Mockito.<Folder>any());
        verify(note).setId(Mockito.<Long>any());
        verify(note).setTitle(Mockito.<String>any());
        verify(note).setTrash(anyBoolean());
        verify(noteRepository).findAll();
        assertTrue(actualSearchByQueryResult.isEmpty());
    }

    /**
     * Method under test: {@link NoteService#searchByQuery(String)}
     */
    @Test
    void testSearchByQuery6() {
        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());
        Note note = mock(Note.class);
        when(note.isArchive()).thenReturn(false);
        when(note.isTrash()).thenReturn(true);
        doNothing().when(note).setArchive(anyBoolean());
        doNothing().when(note).setContent(Mockito.<String>any());
        doNothing().when(note).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(note).setFavorite(anyBoolean());
        doNothing().when(note).setFolder(Mockito.<Folder>any());
        doNothing().when(note).setId(Mockito.<Long>any());
        doNothing().when(note).setTitle(Mockito.<String>any());
        doNothing().when(note).setTrash(anyBoolean());
        note.setArchive(true);
        note.setContent("Not all who wander are lost");
        note.setCreatedDate(LocalDate.of(1970, 1, 1));
        note.setFavorite(true);
        note.setFolder(folder);
        note.setId(1L);
        note.setTitle("Dr");
        note.setTrash(true);

        ArrayList<Note> noteList = new ArrayList<>();
        noteList.add(note);
        when(noteRepository.findAll()).thenReturn(noteList);
        List<SearchResponse> actualSearchByQueryResult = noteService.searchByQuery("Query");
        verify(note).isArchive();
        verify(note).isTrash();
        verify(note).setArchive(anyBoolean());
        verify(note).setContent(Mockito.<String>any());
        verify(note).setCreatedDate(Mockito.<LocalDate>any());
        verify(note).setFavorite(anyBoolean());
        verify(note).setFolder(Mockito.<Folder>any());
        verify(note).setId(Mockito.<Long>any());
        verify(note).setTitle(Mockito.<String>any());
        verify(note).setTrash(anyBoolean());
        verify(noteRepository).findAll();
        assertTrue(actualSearchByQueryResult.isEmpty());
    }
}
