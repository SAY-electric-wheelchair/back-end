package say.backend.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import say.backend.domain.place.PlaceInfo;
import say.backend.domain.report.ReportState;

import java.util.List;

@Getter
@Setter
@Schema(title = "장소 조회 결과 dto")
public class PlaceResDto extends PlaceInfo {
    @Schema(description = "고장여부", example = "")
    private String reportState;

    @Schema(description = "장소 사진", example = "")
    private List<String> placeImgList;
}
