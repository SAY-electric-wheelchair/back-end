package say.backend.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import say.backend.domain.place.PlaceCategory;

@Getter
@Setter
@Schema(title = "장소 생성 dto")
public class PlaceCreateDto {
    @Schema(description = "장소명", example = "")
    private String placeName;
    @Schema(description = "주소", example = "")
    private String address;
    @Schema(description = "상세 주소", example = "")
    private String addressDetail;
    @Schema(description = "장소 카테고리", example = "")
    private PlaceCategory placeCategory;
    @Schema(description = "좌표(경도,위도)", example = "")
    private String coordinate;
}
