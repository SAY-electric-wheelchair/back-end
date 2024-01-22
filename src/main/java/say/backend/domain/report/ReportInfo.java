package say.backend.domain.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import say.backend.domain.common.DelYn;
import say.backend.domain.file.ReportFile;
import say.backend.domain.place.PlaceInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Getter @Setter @Entity
@Table(name = "report_info")
public class ReportInfo {
    @Column(name="report_idx")
    @Id
    private String reportIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="place_idx")
    private PlaceInfo placeIdx;

    @Column(name="content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name="report_state")
    private ReportState reportState;

    @Column(name="reg_dt")
    private LocalDateTime regDt;

    @Column(name="mod_dt")
    private LocalDateTime modDt;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    @Column(name="del_yn")
    private DelYn delYn;

    @JsonIgnore
    @OneToMany(mappedBy = "reportIdx")
    private List<ReportFile> reportFileList = new ArrayList<>();
}
