package ru.otus.integration.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.ServiceSendingEMail;

@Service
public class ServiceSendingEMailImpl implements ServiceSendingEMail {
    @Override
    public PeopleModel sendEMail(PeopleModel peopleModel) {
        System.out.println("Send email to " + peopleModel.getHumanName() + " : " + peopleModel.getMessageValue());
        return peopleModel;

    }
}
