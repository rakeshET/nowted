package com.edstem.project.controller;


import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.TrashResponse;
import com.edstem.project.service.TrashService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trash")
@RequiredArgsConstructor
public class TrashController {

    private final TrashService trashService;

    @PostMapping("/{noteId}")
    public TrashResponse createTrash(@PathVariable Long noteId) {
        return trashService.createTrash(noteId);
    }
    @GetMapping
    public List<TrashResponse> getAllTrashedItems() {
        return trashService.getAllTrashedItems();
    }
    @PutMapping("/{trashId}/restore")
    public NoteResponse restoreTrash(@PathVariable Long trashId) {
        return trashService.restoreTrash(trashId);
    }
    @DeleteMapping("/{id}")
    public void deleteTrash(@PathVariable Long id) {
        trashService.deleteTrash(id);
    }
}