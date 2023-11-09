package com.edstem.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.edstem.project.contract.request.FolderRequest;
import com.edstem.project.contract.response.FolderResponse;
import com.edstem.project.model.Folder;
import com.edstem.project.repository.FolderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FolderService.class})
@ExtendWith(SpringExtension.class)
class FolderServiceTest {
    @MockBean private FolderRepository folderRepository;

    @Autowired private FolderService folderService;

    @MockBean private ModelMapper modelMapper;
    @Test
    void testCreateFolder() {
        // Create a FolderRequest
        FolderRequest request = new FolderRequest("Name");

        // Create a Folder and FolderRespons
        Folder folder = new Folder();
        FolderResponse folderResponse = new FolderResponse();

        when(modelMapper.map(request, Folder.class)).thenReturn(folder);
        when(folderRepository.save(folder)).thenReturn(folder);
        when(modelMapper.map(folder, FolderResponse.class)).thenReturn(folderResponse);

        // Call the method under test
        FolderResponse result = folderService.createFolder(request);

        // Verify the results
        assertNotNull(result);
        assertEquals(folderResponse, result);

        // Verify the interactions with mocked objects
        verify(modelMapper).map(request, Folder.class);
        verify(folderRepository).save(folder);
        verify(modelMapper).map(folder, FolderResponse.class);
    }

    @Test
    void testGetAllFolders() {
        when(folderRepository.findAll()).thenReturn(new ArrayList<>());
        List<FolderResponse> actualAllFolders = folderService.getAllFolders();
        verify(folderRepository).findAll();
        assertTrue(actualAllFolders.isEmpty());
    }

    @Test
    void testGetAllFolders2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FolderResponse>>any()))
                .thenReturn(new FolderResponse());

        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());

        ArrayList<Folder> folderList = new ArrayList<>();
        folderList.add(folder);
        when(folderRepository.findAll()).thenReturn(folderList);
        List<FolderResponse> actualAllFolders = folderService.getAllFolders();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<FolderResponse>>any());
        verify(folderRepository).findAll();
        assertEquals(1, actualAllFolders.size());
    }

    @Test
    void testGetAllFolders3() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FolderResponse>>any()))
                .thenReturn(new FolderResponse());

        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());

        Folder folder2 = new Folder();
        folder2.setId(2L);
        folder2.setName("42");
        folder2.setNotes(new ArrayList<>());

        ArrayList<Folder> folderList = new ArrayList<>();
        folderList.add(folder2);
        folderList.add(folder);
        when(folderRepository.findAll()).thenReturn(folderList);
        List<FolderResponse> actualAllFolders = folderService.getAllFolders();
        verify(modelMapper, atLeast(1))
                .map(Mockito.<Object>any(), Mockito.<Class<FolderResponse>>any());
        verify(folderRepository).findAll();
        assertEquals(2, actualAllFolders.size());
    }

    @Test
    void testGetFolderById() {
        FolderResponse folderResponse = new FolderResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FolderResponse>>any()))
                .thenReturn(folderResponse);

        Folder folder = new Folder();
        folder.setId(1L);
        folder.setName("Name");
        folder.setNotes(new ArrayList<>());
        Optional<Folder> ofResult = Optional.of(folder);
        when(folderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        FolderResponse actualFolderById = folderService.getFolderById(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<FolderResponse>>any());
        verify(folderRepository).findById(Mockito.<Long>any());
        assertSame(folderResponse, actualFolderById);
    }

}
