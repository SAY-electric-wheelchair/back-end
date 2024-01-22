package say.backend.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import say.backend.service.MessageService;

import java.io.IOException;

import static say.backend.domain.common.Constants.SENDER_PHONE_NUMBER;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ARSController {

    private final MessageService messageService;
    private final String voiceMent = "안녕하세요 휠차차입니다 전동보장구 충전소 지도를 보내드리오니 메시지 확인 부탁드립니다 감사합니다";
    private final String messageMent = "휠차차 지도 접속 링크";

    @PostMapping("/voice")
    public void incomingCall(HttpServletRequest request, HttpServletResponse response) {

        Say say = new Say.Builder(voiceMent).language(Say.Language.KO_KR).build();
        VoiceResponse twiml = new VoiceResponse.Builder().say(say).build();

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException | IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000); //3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //메시지 보내기
        String receiver_phone_number = request.getParameter("From");

        messageService.sendMessage(receiver_phone_number, messageMent);

    }

}
