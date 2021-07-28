package ru.otus.integration.service.api;

import ru.otus.integration.model.PeopleModel;

public interface LogService {

    void writeLogSMS(PeopleModel peopleModel);
    void writeLogPush(PeopleModel peopleModel);
    void writeLogEMail(PeopleModel peopleModel);
}
