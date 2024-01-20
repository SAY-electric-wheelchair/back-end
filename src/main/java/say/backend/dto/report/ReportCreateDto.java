package say.backend.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import say.backend.domain.place.PlaceInfo;

import java.util.List;

@Getter
@Setter
@Schema(title = "신고 등록 req")
public class ReportCreateDto {
    @Schema(description = "내용", example = "와라락")
    private String content;

    @Schema(description = "사진 경로", example = "http://faierqnpotvjroe")
    private List<String> photoUrls;

    @Schema(description = "장소 상세페이지에서 들어갔으니까 장소는 default", example = "1")
    private PlaceInfo placeIdx;
}