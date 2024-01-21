package say.backend.domain.file;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReportFileIdx implements Serializable {
    private String reportIdx;
    private String fileIdx;
}
