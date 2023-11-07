package com.edstem.project.service;

import com.edstem.project.contract.request.TrashRequest;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.TrashResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.model.Note;
import com.edstem.project.model.Trash;
import com.edstem.project.repository.NoteRepository;
import com.edstem.project.repository.TrashRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TrashService {
    private final TrashRepository trashRepository;
    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    public TrashService(TrashRepository trashRepository, NoteRepository noteRepository, ModelMapper modelMapper) {
        this.trashRepository = trashRepository;
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }

    public TrashResponse createTrash(TrashRequest trashRequest) {
        Note note = noteRepository.findById(trashRequest.getNoteId()).orElseThrow(() -> new CustomException("Note not found"));
        Trash trash = new Trash();
        trash.setNote(note);
        trash = trashRepository.save(trash);
        return modelMapper.map(trash, TrashResponse.class);
    }

    public NoteResponse restoreTrash(Long id) {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new CustomException("Trash not found"));
        Note note = trash.getNote();
        trashRepository.delete(trash);
        return modelMapper.map(note, NoteResponse.class);
    }

    public void deleteTrash(Long id) {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new CustomException("Trash not found"));
        trashRepository.delete(trash);
    }
}