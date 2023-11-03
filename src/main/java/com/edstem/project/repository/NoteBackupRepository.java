package com.edstem.project.repository;

import com.edstem.project.model.NoteBackup;
import java.time.YearMonth;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteBackupRepository extends JpaRepository<NoteBackup, Long> {
    List<NoteBackup> findByMonth(YearMonth month);
}
