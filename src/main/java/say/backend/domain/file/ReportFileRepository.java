package say.backend.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import say.backend.domain.report.ReportInfo;

import java.util.Optional;

@Repository
public interface ReportFileRepository extends JpaRepository<ReportFile, ReportFileIdx> {
    Optional<ReportFile> findByFileIdx(ReportFileIdx reportFileIdx);
    Optional<ReportFile> findByReportIdx(ReportFileIdx reportFileIdx);
}
