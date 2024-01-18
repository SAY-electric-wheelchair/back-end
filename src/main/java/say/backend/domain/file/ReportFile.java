package say.backend.domain.file;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import say.backend.domain.report.ReportInfo;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Getter @Setter @Entity
@Table(name = "report_file")
public class ReportFile {
    @EmbeddedId
    private ReportFileIdx ReportFileIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="report_idx")
    @MapsId("reportIdx")
    private ReportInfo reportIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_idx")
    @MapsId("fileIdx")
    private FileInfo fileIdx;

}
