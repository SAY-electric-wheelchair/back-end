package say.backend.domain.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsLogRepository extends JpaRepository<SmsLog, String> {
    Optional<SmsLog> findBySmsIdx(String smsIdx);
}
