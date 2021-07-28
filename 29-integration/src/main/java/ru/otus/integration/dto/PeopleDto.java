package ru.otus.integration.dto;

import ru.otus.integration.entity.People;
import ru.otus.integration.model.PeopleModel;

public interface PeopleDto {

    PeopleModel getModelFromEntity(People people, String message);
}
