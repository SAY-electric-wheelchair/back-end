package say.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import say.backend.domain.common.DelYn;
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

    @Transactional
    public ReportInfo createReport(ReportCreateDto pcd) {
        try {
            // create new report object
            ReportInfo newReportInfo = new ReportInfo();

            // set parameters
            String uuid = UUID.randomUUID().toString();
            LocalDateTime date = LocalDateTime.now();
            newReportInfo.setReportIdx(uuid);
            newReportInfo.setPlaceIdx(pcd.getPlaceIdx());
            newReportInfo.setContent(pcd.getContent());
            newReportInfo.setRegDt(date);
            newReportInfo.setModDt(date);
            newReportInfo.setDelYn(DelYn.N);

            // create report file and add it to the list
            List<String> photoUrls = reportCreateDto.getPhotoUrls();
            for (String photoUrl : photoUrls) {
                ReportFile reportFile = new ReportFile();
                reportFile.setSaveFileName(photoUrl); // save_file_name이 ReportFile의 필드라고 가정합니다
                // TODO: 이미지 업로드 서비스 호출 및 S3에 저장된 파일 이름 가져오기
                String storedFileName = ""; // 이미지 업로드 서비스 호출 부분을 추가해야 합니다.
                reportFile.setFileUrl(storedFileName);
                reportFile.setRegDt(date);
                reportFile.setModDt(date);
                newReportInfo.getReportFileList().add(reportFile);
            }

            // insert new data
            ReportInfo resultData = reportInfoRepository.save(newReportInfo);

            return resultData;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }
}
