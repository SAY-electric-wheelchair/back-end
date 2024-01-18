package say.backend.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import say.backend.domain.log.SmsLog;
import say.backend.domain.log.SmsLogRepository;
import say.backend.domain.log.SmsState;

import static say.backend.domain.common.Constants.SENDER_PHONE_NUMBER;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SmsLogRepository smsLogRepository;

    public void sendMessage(String callNumberBySender, String content){
        SmsLog smsLog = SmsLog.builder()
                .smsTel(callNumberBySender)
                .build();
        try{

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
