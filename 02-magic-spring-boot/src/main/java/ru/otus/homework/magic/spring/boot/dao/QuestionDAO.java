package ru.otus.homework.magic.spring.boot.dao;

import ru.otus.homework.magic.spring.boot.domain.Question;
import ru.otus.homework.magic.spring.boot.parser.DataRow;

public interface QuestionDAO {

    Question getQuestion(DataRow dataRow);

    String getQuestionLocalised(Question question);
}
