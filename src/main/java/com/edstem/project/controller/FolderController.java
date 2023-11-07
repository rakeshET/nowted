package com.edstem.project.controller;

import com.edstem.project.contract.request.FolderRequest;
import com.edstem.project.contract.response.FolderResponse;
import com.edstem.project.exception.CustomException;
import com.edstem.project.service.FolderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/folders")
public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public FolderResponse createFolder(@RequestBody FolderRequest request) {
        return folderService.createFolder(request);
    }

    @GetMapping
    public List<FolderResponse> getAllFolders() {
        return folderService.getAllFolders();
    }

    @GetMapping("/{id}")
    public FolderResponse getFolderById(@PathVariable Long id) {
        FolderResponse folder = folderService.getFolderById(id);
        if (folder == null) {
            throw new CustomException("Folder not found with ID: " + id);
        }
        return folder;
    }

}
