package ru.otus.integration.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.ServiceSendingPushMessages;

@Service
public class ServiceSendingPushMessagesImpl implements ServiceSendingPushMessages {
    @Override
    public PeopleModel sendPushMessage(PeopleModel peopleModel) {
        System.out.println("Send push message to " + peopleModel.getHumanName() + " : " + peopleModel.getMessageValue());
        return peopleModel;
    }
}
