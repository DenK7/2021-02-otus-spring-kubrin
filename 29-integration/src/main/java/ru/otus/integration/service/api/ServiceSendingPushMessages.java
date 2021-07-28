package ru.otus.integration.service.api;

import ru.otus.integration.model.PeopleModel;

public interface ServiceSendingPushMessages {

    PeopleModel sendPushMessage(PeopleModel peopleModel);
}
