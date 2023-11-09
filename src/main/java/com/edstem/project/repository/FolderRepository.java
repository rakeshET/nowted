package com.edstem.project.repository;

import com.edstem.project.model.Folder;
import java.util.Arrays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    Arrays findByNameContaining(String query);
}
