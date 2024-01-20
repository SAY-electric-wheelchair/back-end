package say.backend.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "신고 등록 res")
public class ReportResDto {
    @Schema(description = "신고 등록 번호", example = "1")
    private Long ReportId;
}