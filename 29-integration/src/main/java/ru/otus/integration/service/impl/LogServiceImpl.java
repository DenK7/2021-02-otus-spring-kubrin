package ru.otus.integration.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.LogService;

@Service
public class LogServiceImpl implements LogService {

    @Override
    public void writeLogSMS(PeopleModel peopleModel) {
        System.out.println("Write to log about messages to " + peopleModel.getHumanName() +
                ", message -> " + peopleModel.getMessageValue() +", sms message");
    }

    @Override
    public void writeLogPush(PeopleModel peopleModel) {
        System.out.println("Write to log about messages to " + peopleModel.getHumanName() +
                ", message -> " + peopleModel.getMessageValue() + ", push message");
    }

    @Override
    public void writeLogEMail(PeopleModel peopleModel) {
        System.out.println("Write to log about messages to " + peopleModel.getHumanName() +
                ", message -> " + peopleModel.getMessageValue() + ", email");
    }
}
