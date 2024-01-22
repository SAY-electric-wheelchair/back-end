// TODO: erd 설계 완료 후 삭제 예정
package say.backend.controller;

import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import say.backend.dto.test.TestDto;
import say.backend.exception.common.BusinessException;
import say.backend.response.BaseResponse;
import say.backend.service.TestService;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @GetMapping("/result-msg")
    public BaseResponse<String> getResultMsg(@RequestBody TestDto testDto) {
        try {
            String result = testService.getResultMsg(testDto.isSuccess());
            return new BaseResponse<>(result);
        } catch(BusinessException e) {
            return new BaseResponse<>(e.getErrorCode());
        }
    }
}
