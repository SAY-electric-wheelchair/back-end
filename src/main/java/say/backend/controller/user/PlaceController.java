package say.backend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import say.backend.domain.place.PlaceCategory;
import say.backend.domain.place.PlaceInfo;
import say.backend.dto.place.PlaceSearchDto;
import say.backend.exception.common.BusinessException;
import say.backend.response.BaseResponse;
import say.backend.service.PlaceInfoService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "(사용자) 장소 API")
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceInfoService placeInfoService;

    @Operation(summary = "장소 상세 정보 조회", description = "idx에 해당하는 장소 정보 1개 반환")
    @GetMapping("/{placeIdx}")
    public BaseResponse<PlaceInfo> getPlaceDetail(@Parameter(description = "장소고유번호" )@PathVariable("placeIdx") String placeIdx) {
        try{
            // call service
            PlaceInfo resultData = placeInfoService.getPlaceDetail(placeIdx);
            return new BaseResponse<PlaceInfo>(resultData);
        } catch(BusinessException e) {
            return new BaseResponse(e.getErrorCode());
        }
    }

//    @Operation(summary="장소 리스트 조회", description = "필터링 조건에 맞는 장소 리스트 반환" +
//            " (주의사항: placeCategory로 enum 값 외의 값을 주면 400error 뜸.)")
//    @GetMapping("/list")
//    public BaseResponse<List<PlaceInfo>> getPlaceList(@RequestBody PlaceSearchDto placeSearchDto) {
//        try{
//            // call Service
//            List<PlaceInfo> resultData = placeInfoService.getPlaceList(placeSearchDto.getPlaceName(), placeSearchDto.getPlaceCategoryList());
//            return new BaseResponse<List<PlaceInfo>>(resultData);
//        } catch(BusinessException e) {
//            return new BaseResponse(e.getErrorCode());
//        }
//    }

    @Operation(summary="장소 리스트 조회", description = "필터링 조건에 맞는 장소 리스트 반환" +
            " (주의사항: placeCategory로 enum 값 외의 값을 주면 400error 뜸.)")
    @GetMapping("/list")
    public BaseResponse<List<PlaceInfo>> getPlaceList(
            @RequestParam(value = "placeName", required = false) String placeName,
            @RequestParam(value = "placeCategoryList", required = false) List<PlaceCategory> placeCategoryList) {
        try {
            // call Service
            List<PlaceInfo> resultData = placeInfoService.getPlaceList(placeName, placeCategoryList);
            return new BaseResponse<>(resultData);
        } catch (BusinessException e) {
            return new BaseResponse<>(e.getErrorCode());
        }
    }
}
