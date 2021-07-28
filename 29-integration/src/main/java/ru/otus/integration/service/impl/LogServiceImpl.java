package ru.otus.integration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.LogService;

@Service
public class LogServiceImpl implements LogService {
    Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    // добавил логгер для отслеживания настроек в application.yml, а именно ru.otus: error

    @Override
    public void writeLogSMS(PeopleModel peopleModel) {
        logger.info( "Write to log about messages to " + peopleModel.getHumanName() +
                ", message -> " + peopleModel.getMessageValue() +", sms message");
    }

    @Override
    public void writeLogPush(PeopleModel peopleModel) {
        logger.warn("Write to log about messages to " + peopleModel.getHumanName() +
                ", message -> " + peopleModel.getMessageValue() + ", push message");
    }

    @Override
    public void writeLogEMail(PeopleModel peopleModel) {
        logger.error("Write to log about messages to " + peopleModel.getHumanName() +
                ", message -> " + peopleModel.getMessageValue() + ", email");
    }
}
