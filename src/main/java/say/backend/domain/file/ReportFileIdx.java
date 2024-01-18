package say.backend.domain.file;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReportFileIdx {
    private String reportIdx;
    private String fileIdx;
}
