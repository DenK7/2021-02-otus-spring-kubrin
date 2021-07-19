package ru.otus.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PEOPLE")
@NoArgsConstructor
@AllArgsConstructor
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HUMAN_NAME")
    private String humanName;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "SEND_MAIL")
    private int sendMail;

    @Column(name = "SEND_PUSH_MESSAGE")
    private int sendPushMessage;

    @Column(name = "SEND_SMS")
    private int sendSMS;
}
