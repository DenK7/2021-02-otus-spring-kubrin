package ru.otus.integration.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.integration.dto.PeopleDto;
import ru.otus.integration.entity.People;
import ru.otus.integration.gateway.PeopleGateway;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.repositories.PeopleRepositories;
import ru.otus.integration.service.api.MessageService;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final String RESILIENCE_INSTANCE = "integration";

    private final PeopleRepositories peopleRepositories;
    private final PeopleDto peopleDto;
    private final PeopleGateway peopleGateway;

    @Override
    @TimeLimiter(name = RESILIENCE_INSTANCE)
    @CircuitBreaker(name = RESILIENCE_INSTANCE, fallbackMethod = "sendMessagesFallback")
    public String sendMessages(String message) {
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

    public String sendMessagesFallback(TimeoutException ex) {
        return "Messages not send: " + ex.getMessage();
    }
}
