package ru.otus.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.integration.dto.PeopleDto;
import ru.otus.integration.entity.People;
import ru.otus.integration.gateway.PeopleGateway;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.repositories.PeopleRepositories;
import ru.otus.integration.service.api.MessageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final PeopleRepositories peopleRepositories;
    private final PeopleDto peopleDto;
    private final PeopleGateway peopleGateway;

    @Override
    public void sendMessages(String message) {
        Collection<PeopleModel> peopleModelList = new ArrayList<>();
        List<People> peopleList = peopleRepositories.findAll();
        for (People people : peopleList) {
            peopleModelList.add(peopleDto.getModelFromEntity(people, message));
        }

        System.out.println("start send message ------> " + message+ "; ------ cnt >" + peopleModelList.size());
        peopleGateway.process(peopleModelList);
    }
}
