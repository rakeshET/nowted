package com.edstem.project.service;

import com.edstem.project.contract.request.FolderRequest;
import com.edstem.project.contract.response.FolderResponse;
import com.edstem.project.model.Folder;
import com.edstem.project.repository.FolderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired private ModelMapper modelMapper;

    public FolderResponse createFolder(FolderRequest request) {
        Folder folder = modelMapper.map(request, Folder.class);
        folder = folderRepository.save(folder);
        return modelMapper.map(folder, FolderResponse.class);
    }

    public List<FolderResponse> getAllFolders() {
        List<Folder> folders = folderRepository.findAll();
        return folders.stream()
                .map(folder -> modelMapper.map(folder, FolderResponse.class))
                .collect(Collectors.toList());
    }

    public FolderResponse getFolderById(Long id) {
        Folder folder = folderRepository.findById(id).orElse(null);
        if (folder == null) {
            return null;
        }
        return modelMapper.map(folder, FolderResponse.class);
    }
}
