package ru.otus.integration.dto;

import org.springframework.stereotype.Component;
import ru.otus.integration.entity.People;
import ru.otus.integration.model.PeopleModel;

@Component
public class PeopleDtoImpl implements PeopleDto{
    @Override
    public PeopleModel getModelFromEntity(People people, String message) {
        return PeopleModel.builder()
                .id(people.getId())
                .humanName(people.getHumanName())
                .sex(people.getSex())
                .sendEMail(people.getSendMail() == 1)
                .sendPushMessage(people.getSendPushMessage() == 1)
                .sendSMSMessage(people.getSendSMS() == 1)
                .messageValue(message)
                .build();
    }
}
