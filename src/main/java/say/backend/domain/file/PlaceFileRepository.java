package say.backend.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceFileRepository extends JpaRepository<PlaceFile, PlaceFileIdx> {
    Optional<PlaceFile> findByFileIdx(PlaceFileIdx placeFileIdx);
    Optional<PlaceFile> findByPlaceIdx(PlaceFileIdx placeFileIdx);
}
