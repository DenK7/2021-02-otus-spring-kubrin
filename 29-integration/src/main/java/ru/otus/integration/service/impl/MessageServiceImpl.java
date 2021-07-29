package ru.otus.integration.service.impl;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    private final String RESILIENCE_INSTANCE = "integration";

    private final PeopleRepositories peopleRepositories;
    private final PeopleDto peopleDto;
    private final PeopleGateway peopleGateway;

    @Override
    @CircuitBreaker(name = RESILIENCE_INSTANCE, fallbackMethod = "sendMessagesFallback")
    public String sendMessages(String message) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        Collection<PeopleModel> peopleModelList = new ArrayList<>();
        List<People> peopleList = peopleRepositories.findAll();
        for (People people : peopleList) {
            peopleModelList.add(peopleDto.getModelFromEntity(people, message));
        }

        if (peopleModelList.size() > 0) {
            System.out.println("start send message ------> " + message + "; ------ cnt >" + peopleModelList.size());
            peopleGateway.process(peopleModelList);
        } else {
            System.out.println("Database not ready to work, row count = 0");
        }
        return "Messages send";
    }

    private String sendMessagesFallback(CallNotPermittedException ex) {
        return "Messages not send (CallNotPermittedException): " + ex.getMessage();
    }

    private String sendMessagesFallback(Exception ex) {
        return "Messages not send (Exception): " + ex.getMessage();
    }

}
