package ru.otus.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.integration.model.PeopleModel;

import java.util.Collection;

@MessagingGateway
public interface PeopleGateway {

    @Gateway(requestChannel = "requestPeopleChannel")
    void process(Collection<PeopleModel> orderItem);

}
