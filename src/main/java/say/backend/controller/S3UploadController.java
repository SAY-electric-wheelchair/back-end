package say.backend.controller;

import say.backend.dto.s3.UploadPhotoDTO;
import say.backend.service.S3Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(produces = "application/json;charset=utf8")
public class S3UploadController {
    @Autowired
    private S3Uploader s3Uploader;

    @PostMapping(value = "/uploadNewImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadPhotoDTO> uploadImg(@RequestParam("file") MultipartFile image) throws IOException {
        UploadPhotoDTO userImgDTO = new UploadPhotoDTO();
        System.out.println("Upload Img");
        System.out.println(image);
        System.out.println("------------------------------------------------------");
        if (!image.isEmpty()) {
            String storedFileName = s3Uploader.upload(image, "images");
            userImgDTO.setUploadImg(storedFileName);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userImgDTO);
    }
}