package say.backend.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import say.backend.domain.file.FileInfo;
import say.backend.domain.file.FileType;
import say.backend.domain.place.PlaceInfo;

import java.util.List;

@Getter
@Setter
@Schema(title = "파일 생성 req")
public class FileCreateDto {
    @Schema(description = "기존 파일명", example = "shinjisu")
    private FileInfo originalFileName;

    @Schema(description = "파일 타입", example = "jpg")
    private FileType fileType;
}
