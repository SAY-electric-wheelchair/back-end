package say.backend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import say.backend.domain.report.ReportInfo;
import say.backend.domain.report.ReportInfoRepository;
import say.backend.dto.report.ReportCreateDto;
import say.backend.dto.report.ReportResDto;
import say.backend.exception.common.BusinessException;
import say.backend.exception.common.ErrorCode;
import say.backend.response.BaseResponse;
import say.backend.service.ReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping( value = "/api/report",  produces = "application/json;charset=utf8")
@Tag(name = "(사용자) 신고 API")
public class ReportController {
    private final ReportService reportService;
    private final ReportInfoRepository reportInfoRepository;

    public ReportController(ReportService reportService, ReportInfoRepository reportInfoRepository) {
        this.reportService = reportService;
        this.reportInfoRepository = reportInfoRepository;
    }

    @Operation(summary = "신고 등록", description = "신고 사진과 글 등록")
    @PostMapping("/create")
    public BaseResponse<ReportInfo> createReport(@RequestBody ReportCreateDto reportCreateDto) {
        try{
            // validation
            if (reportCreateDto.getPlaceIdx() == null
                    || reportCreateDto.getImgList().stream().allMatch(url -> url == null)) {
                throw new BusinessException(ErrorCode.EMPTY_DATA);
            }

            ReportInfo resultData = reportService.createReport(reportCreateDto);
            return new BaseResponse<ReportInfo>(resultData);
        } catch(BusinessException e) {
            return new BaseResponse(e.getErrorCode());
        }
    }

}
