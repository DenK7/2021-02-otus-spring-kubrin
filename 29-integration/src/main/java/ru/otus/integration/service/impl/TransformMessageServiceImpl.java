package ru.otus.integration.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.TransformMessageService;

@Service
public class TransformMessageServiceImpl implements TransformMessageService {

    @Override
    // преобразование сообщения, добавление каких то подписей и пр
    public PeopleModel transformMessage(PeopleModel peopleModel) {
        String msg = peopleModel.getMessageValue().replace("[name]",
                peopleModel.getSex().equals("M") ? "Mr." + peopleModel.getHumanName() : "Ms." + peopleModel.getHumanName());
        System.out.println("transform message ------> " + msg);
        peopleModel.setMessageValue(msg);
        return peopleModel;
    }
}
