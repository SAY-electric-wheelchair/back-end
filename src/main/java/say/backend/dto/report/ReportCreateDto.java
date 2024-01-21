package say.backend.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import say.backend.domain.file.FileType;
import say.backend.domain.place.PlaceInfo;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Schema(title = "신고 등록 req")
public class ReportCreateDto {
    @Schema(description = "장소 상세페이지에서 들어갔으니까 장소는 default", example = "1")
    private PlaceInfo placeIdx;

    @Schema(description = "내용", example = "1번 장소 신고 메시지 테스트")
    private String content;

    @Schema(description = "사진 리스트", example = "https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg")
    private List<MultipartFile> imgList;
}