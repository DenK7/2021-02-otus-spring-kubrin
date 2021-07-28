package ru.otus.integration.service.api;

import ru.otus.integration.model.PeopleModel;

public interface ServiceSendingSMSMessages {

    PeopleModel sendSMSMessage(PeopleModel peopleModel);
}
