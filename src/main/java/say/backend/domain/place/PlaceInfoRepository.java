package say.backend.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import say.backend.domain.common.DelYn;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceInfoRepository extends JpaRepository<PlaceInfo, String> {
    Optional<PlaceInfo> findByPlaceIdx(String placeIdx);

    @Query("SELECT p FROM PlaceInfo p " +
            "WHERE (:placeName IS NULL OR p.placeName LIKE %:placeName%) " +
            "AND (:placeCategory IS NULL OR p.placeCategory = :placeCategory) " +
            "AND (:delYn IS NULL OR p.delYn = :delYn)")
    List<PlaceInfo> findByPlaceNameAndPlaceCategoryAndDelYn(
            @Param("placeName") String placeName,
            @Param("placeCategory") PlaceCategory placeCategory,
            @Param("delYn") DelYn delYn
    );

    @Query("SELECT p FROM PlaceInfo p " +
            "WHERE (:placeName IS NULL OR p.placeName LIKE %:placeName%) " +
            "AND (:placeCategoryList IS NULL OR p.placeCategory IN :placeCategoryList) " +
            "AND (:delYn IS NULL OR p.delYn = :delYn)")
    List<PlaceInfo> findByPlaceNameAndPlaceCategoryListAndDelYn(
            @Param("placeName") String placeName,
            @Param("placeCategoryList") List<PlaceCategory> placeCategoryList,
            @Param("delYn") DelYn delYn
    );
}
