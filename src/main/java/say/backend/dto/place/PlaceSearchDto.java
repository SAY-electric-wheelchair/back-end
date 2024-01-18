package say.backend.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import say.backend.domain.place.PlaceCategory;

import java.util.List;

@Setter
@Getter
@Schema(title = "장소 검색 조건 dto")
public class PlaceSearchDto {
    @Schema(description = "장소명", example = "동대문역 전동휠체어급속충전기")
    private String placeName;
    @Schema(description = "장소 카테고리", example = "CHARGE")
    private PlaceCategory placeCategory;
    @Schema(description = "장소 카테고리 리스트", example = "[CHARGE, REPAIR]")
    private List<PlaceCategory> placeCategoryList;
}
