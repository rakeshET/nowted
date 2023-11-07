package com.edstem.project.service;

import com.edstem.project.contract.request.NoteRequest;
import com.edstem.project.contract.response.NoteArchivedResponse;
import com.edstem.project.contract.response.NoteFavoriteResponse;
import com.edstem.project.contract.response.NoteInFolderResponse;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.model.Folder;
import com.edstem.project.model.Note;
import com.edstem.project.repository.FolderRepository;
import com.edstem.project.repository.NoteRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final FolderRepository folderRepository;

    private final ModelMapper modelMapper;

    public NoteResponse createNote(NoteRequest noteRequest) {
        Optional<Folder> folderOptional =
                folderRepository.findById(noteRequest.getFolder().getId());
        if (!folderOptional.isPresent()) {
            throw new CustomException("Folder not found");
        }

        Note note = modelMapper.map(noteRequest, Note.class);

        Note builtNote =
                Note.builder()
                        .id(note.getId())
                        .title(note.getTitle())
                        .content(note.getContent())
                        .createdDate(note.getCreatedDate())
                        .folder(folderOptional.get())
                        .build();

        builtNote = noteRepository.save(builtNote);

        return modelMapper.map(builtNote, NoteResponse.class);
    }

    public List<NoteResponse> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(note -> modelMapper.map(note, NoteResponse.class))
                .collect(Collectors.toList());
    }

    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            return null;
        }
        return modelMapper.map(note, NoteResponse.class);
    }

    public NoteResponse updateNote(Long id, NoteRequest request) {
        Note existingNote = noteRepository.findById(id).orElse(null);
        if (existingNote == null) {
            return null;
        }

        existingNote.setTitle(request.getTitle());
        existingNote.setContent(request.getContent());

        existingNote = noteRepository.save(existingNote);

        return modelMapper.map(existingNote, NoteResponse.class);
    }

//    public void deleteNote(Long id) {
//        noteRepository.deleteById(id);
//    }

    public List<NoteInFolderResponse> getAllNotesInFolder(Long folderId) {
        List<Note> notesInFolder = noteRepository.findByFolderId(folderId);
        return notesInFolder.stream()
                .map(note -> modelMapper.map(note, NoteInFolderResponse.class))
                .collect(Collectors.toList());
    }

    public List<NoteFavoriteResponse> getFavoriteNotes() {
        List<Note> favoriteNotes = noteRepository.findByFavoriteTrue();
        return favoriteNotes.stream()
                .map(note -> modelMapper.map(note, NoteFavoriteResponse.class))
                .collect(Collectors.toList());
    }

    public NoteFavoriteResponse addNoteToFavorites(Long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note != null) {
            note.setFavorite(true);
            note = noteRepository.save(note);

            NoteFavoriteResponse response = modelMapper.map(note, NoteFavoriteResponse.class);
            return response;
        }
        return null;
    }

    public NoteFavoriteResponse removeNoteFromFavorites(Long id) {
        Note note = noteRepository.findById(id).orElse(null);

        if (note != null) {
            note.setFavorite(false);
            noteRepository.save(note);
            return modelMapper.map(note, NoteFavoriteResponse.class);
        } else {
            throw new CustomException("Note not found with ID: " + id);
        }
    }

    public List<NoteArchivedResponse> getArchivedNotes() {
        List<Note> archivedNotes = noteRepository.findByArchiveTrue();
        return archivedNotes.stream()
                .map(note -> modelMapper.map(note, NoteArchivedResponse.class))
                .collect(Collectors.toList());
    }

    public NoteArchivedResponse archiveNote(Long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note != null) {
            note.setArchive(true);
            note = noteRepository.save(note);

            NoteArchivedResponse response = modelMapper.map(note, NoteArchivedResponse.class);
            return response;
        }
        return null;
    }

    public NoteArchivedResponse unarchiveNote(Long id) {
        Note note = noteRepository.findById(id).orElse(null);

        if (note != null) {
            note.setArchive(false);
            note = noteRepository.save(note);
            return modelMapper.map(note, NoteArchivedResponse.class);
        } else {
            throw new CustomException("Note not found with ID: " + id);
        }
    }
}
