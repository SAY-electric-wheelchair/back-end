package say.backend.dto.s3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UploadPhotoDTO {
    private String uploadImg;
}
