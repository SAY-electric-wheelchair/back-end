package say.backend.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import say.backend.domain.log.SmsLog;
import say.backend.domain.log.SmsLogRepository;
import say.backend.domain.log.SmsState;

import java.util.UUID;

import static say.backend.domain.common.Constants.SENDER_PHONE_NUMBER;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SmsLogRepository smsLogRepository;

    @Value("${api.twilio.id}")
    private String twilio_id;

    @Value("${api.twilio.token}")
    private String twilio_token;

    public void sendMessage(String callNumberBySender, String content){
        SmsLog smsLog = SmsLog.builder()
                .smsIdx(UUID.randomUUID().toString())
                .smsTel(callNumberBySender)
                .build();
        try{

            Twilio.init(twilio_id, twilio_token);

            Message.creator(
                    new PhoneNumber(callNumberBySender),
                    new PhoneNumber(SENDER_PHONE_NUMBER),
                    content
            ).create();
            smsLog.setSmsState(SmsState.SUCCESS);
            smsLogRepository.save(smsLog);

        }catch (RuntimeException e){

            smsLog.setSmsState(SmsState.FAIL);
            smsLogRepository.save(smsLog);
            throw e;
        }

    }

}
