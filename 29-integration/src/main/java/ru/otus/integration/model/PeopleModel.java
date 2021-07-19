package ru.otus.integration.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeopleModel {
    private Long id;
    private String humanName;
    private String sex;
    private boolean sendEMail;
    private boolean sendPushMessage;
    private boolean sendSMSMessage;
    private String messageValue;
}
