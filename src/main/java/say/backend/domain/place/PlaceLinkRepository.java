package say.backend.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceLinkRepository extends JpaRepository<PlaceLink, String> {
    Optional<PlaceLink> findByLinkIdx(String linkIdx);
}
