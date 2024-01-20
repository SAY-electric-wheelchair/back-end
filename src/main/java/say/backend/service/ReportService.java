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
            ReportFile reportFile = new ReportFile();
            reportFile.setSaveFileName(pcd.getPhotoUrl());
            // List로 받아야 함.
            // 사진 저장하는 api 따로 파기
            newReportInfo.getReportFileList().add(reportFile);

            // insert new data
            ReportInfo resultData = reportInfoRepository.save(newReportInfo);

            return resultData;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }
}
