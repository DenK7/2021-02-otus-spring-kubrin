package ru.otus.homework.magic.spring.boot.dao;

import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.parser.DataRow;

public interface AnswerDAO {

    Answer getAnswer(DataRow dataRow);
    String getAnswerLocalised(Answer answer);
}
