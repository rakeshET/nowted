package com.edstem.project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edstem.project.contract.request.FolderRequest;
import com.edstem.project.contract.response.FolderResponse;
import com.edstem.project.service.FolderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class FolderControllerTest {

    @InjectMocks private FolderController folderController;

    @Mock private FolderService folderService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(folderController).build();
    }

    @Test
    public void testCreateFolder() throws Exception {
        FolderRequest request = FolderRequest.builder().name("Test Folder").build();
        FolderResponse response = FolderResponse.builder().id(1L).name("Test Folder").build();

        when(folderService.createFolder(any(FolderRequest.class))).thenReturn(response);

        mockMvc.perform(
                        post("/v1/folders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFolders() throws Exception {
        FolderResponse response = FolderResponse.builder().id(1L).name("Test Folder").build();
        List<FolderResponse> allFolders = Arrays.asList(response);

        when(folderService.getAllFolders()).thenReturn(allFolders);

        mockMvc.perform(get("/v1/folders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFolderById() throws Exception {
        FolderResponse response = FolderResponse.builder().id(1L).name("Test Folder").build();

        when(folderService.getFolderById(anyLong())).thenReturn(response);

        mockMvc.perform(get("/v1/folders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
