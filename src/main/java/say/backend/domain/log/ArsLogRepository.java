package say.backend.domain.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArsLogRepository extends JpaRepository<ArsLog, String> {
    Optional<ArsLog> findByArsIdx(String arsIdx);
}
