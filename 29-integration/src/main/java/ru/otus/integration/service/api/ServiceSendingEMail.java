package ru.otus.integration.service.api;

import ru.otus.integration.model.PeopleModel;

public interface ServiceSendingEMail {

    PeopleModel sendEMail(PeopleModel peopleModel);
}
