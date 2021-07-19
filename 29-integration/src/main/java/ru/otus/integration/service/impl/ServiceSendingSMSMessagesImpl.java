package ru.otus.integration.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.ServiceSendingSMSMessages;

@Service
public class ServiceSendingSMSMessagesImpl implements ServiceSendingSMSMessages {
    @Override
    public PeopleModel sendSMSMessage(PeopleModel peopleModel) {
        System.out.println("Send sms message to " + peopleModel.getHumanName() + " : " + peopleModel.getMessageValue());
        return peopleModel;
    }
}
