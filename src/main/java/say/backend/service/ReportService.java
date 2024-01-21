package say.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import say.backend.domain.common.DelYn;
import say.backend.domain.file.FileInfo;
import say.backend.domain.file.ReportFile;
import say.backend.domain.file.ReportFileRepository;
import say.backend.domain.report.ReportInfo;
import say.backend.domain.report.ReportInfoRepository;
import say.backend.dto.report.ReportCreateDto;
import say.backend.dto.report.ReportResDto;
import say.backend.exception.common.BusinessException;
import say.backend.exception.common.ErrorCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportInfoRepository reportInfoRepository;
    private final ReportFileRepository reportFileRepository;
    private final S3Uploader s3Uploader; // S3Uploader 추가

    @Transactional
    public ReportInfo createReport(ReportCreateDto reportCreateDto) {
        try {
            // 새로운 객체 생성
            ReportInfo newReportInfo = new ReportInfo();
            FileInfo newFileInfo = new FileInfo();
            ReportFile newReportFile = new ReportFile();

            LocalDateTime date = LocalDateTime.now();

            // ReportInfo 채우기
            String uuidReport = UUID.randomUUID().toString();
            newReportInfo.setReportIdx(uuidReport);
            newReportInfo.setPlaceIdx(reportCreateDto.getPlaceIdx());
            newReportInfo.setContent(reportCreateDto.getContent());
            newReportInfo.setRegDt(date);
            newReportInfo.setModDt(date);
            newReportInfo.setDelYn(DelYn.N);

            // FileInfo 채우기
            List<MultipartFile> imgList = reportCreateDto.getImgList();
            for (MultipartFile img : imgList) {
                String uuidImg = UUID.randomUUID().toString();
                newFileInfo.setFileIdx(uuidImg);
//                newFileInfo.setOriginalFileName(newFileInfo.);  //직접 파일을 뜯어 넣기
//                newFileInfo.setFileType();  //직접 파일을 뜯어 넣기
                newFileInfo.setFileName(uuidImg + newFileInfo.getFileType());
                newFileInfo.setSaveFileName("https://" + newFileInfo.getFileName());    // 폴더명 뭐시기 해서 넣기

                // 이미지 업로드 서비스 호출
//                MultipartFile multipartFile = s3Uploader.upload(newFileInfo);

                // reportfile create
                newReportFile.setFileIdx(newFileInfo);
                newReportFile.setReportIdx(newReportInfo);
            }
            return newReportInfo;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }
}