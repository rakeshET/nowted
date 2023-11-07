package com.edstem.project.service;

import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.TrashResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.model.Folder;
import com.edstem.project.model.Note;
import com.edstem.project.model.Trash;
import com.edstem.project.repository.FolderRepository;
import com.edstem.project.repository.NoteRepository;
import com.edstem.project.repository.TrashRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashService {

    private final TrashRepository trashRepository;
    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;
    private final FolderRepository folderRepository;


    public TrashResponse createTrash(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new CustomException("Note not found"));

        Folder folder = note.getFolder();
        folder.getNotes().remove(note);
        note.setFolder(null);

        folderRepository.save(folder);
        noteRepository.save(note);

        Trash trash = new Trash();
        trash.setNote(note);
        trash.setFolderId(folder.getId());
        trash.setFolderName(folder.getName());
        trash = trashRepository.save(trash);

        return modelMapper.map(trash, TrashResponse.class);
    }

    public NoteResponse restoreTrash(Long id) {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new CustomException("Trash not found"));
        Note note = trash.getNote();

        Folder folder = folderRepository.findById(trash.getFolderId()).orElseThrow(() -> new CustomException("Folder not found"));  // Get the original folder using the stored ID
        folder.getNotes().add(note);
        note.setFolder(folder);

        folderRepository.save(folder);
        noteRepository.save(note);

        trashRepository.delete(trash);

        return modelMapper.map(note, NoteResponse.class);
    }

    public List<TrashResponse> getAllTrashedItems() {
        List<Trash> trashes = trashRepository.findAll();
        return trashes.stream()
                .map(trash -> modelMapper.map(trash, TrashResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteTrash(Long id) {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new CustomException("Trash not found"));
        trashRepository.delete(trash);
    }
}