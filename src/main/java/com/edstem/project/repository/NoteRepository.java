package com.edstem.project.repository;

import com.edstem.project.model.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByFolderId(Long folderId);

    List<Note> findByFavoriteTrue();

    List<Note> findByArchiveTrue();

    List<Note> findByTrashTrue();

}
