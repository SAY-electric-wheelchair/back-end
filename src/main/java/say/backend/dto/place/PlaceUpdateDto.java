package say.backend.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "장소 정보 업데이트 dto")
public class PlaceUpdateDto extends PlaceCreateDto {
    @Schema(description = "장소고유번호", example = "")
    private String placeIdx;
}
