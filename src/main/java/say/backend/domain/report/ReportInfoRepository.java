package say.backend.domain.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportInfoRepository extends JpaRepository<ReportInfo, String> {
    Optional<ReportInfo> findByReportIdx(String reportIdx);
}
