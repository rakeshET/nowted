package com.edstem.project.controller;


import com.edstem.project.contract.request.TrashRequest;
import com.edstem.project.contract.response.NoteResponse;
import com.edstem.project.contract.response.TrashResponse;
import com.edstem.project.service.TrashService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trash")
public class TrashController {
    private final TrashService trashService;

    public TrashController(TrashService trashService) {
        this.trashService = trashService;
    }

    @PostMapping
    public TrashResponse createTrash(@RequestBody TrashRequest trashRequest) {
        return trashService.createTrash(trashRequest);
    }

    @PutMapping("/{id}/restore")
    public NoteResponse restoreTrash(@PathVariable Long id) {
        return trashService.restoreTrash(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrash(@PathVariable Long id) {
        trashService.deleteTrash(id);
    }
}