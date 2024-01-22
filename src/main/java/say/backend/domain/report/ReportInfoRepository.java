package say.backend.domain.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import say.backend.domain.place.PlaceInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportInfoRepository extends JpaRepository<ReportInfo, String> {
    Optional<ReportInfo> findByReportIdx(String reportIdx);

    @Query("SELECT r.reportState FROM ReportInfo r WHERE r.placeIdx = :placeIdx")
    List<ReportState> findByPlaceIdx(@Param("placeIdx") PlaceInfo placeIdx);
}
