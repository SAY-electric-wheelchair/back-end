package say.backend.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import say.backend.domain.place.PlaceInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, String> {
    Optional<FileInfo> findByFileIdx(String fileIdx);

    @Query("SELECT f.saveFileName " +
            "FROM FileInfo f JOIN f.placeFileList pf " +
            "WHERE pf.placeIdx = :placeIdx " +
            "ORDER BY f.regDt DESC")
    List<String> findSaveFileNamesByPlaceIdx(@Param("placeIdx") PlaceInfo placeIdx);
}
