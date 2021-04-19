package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.domain.Question;
import ru.otus.homework.magic.spring.boot.parser.DataRow;

import java.util.ArrayList;
import java.util.List;

@Component("QuestionDAO")
public class QuestionDAOImpl implements QuestionDAO {
    private final String locale;

    public QuestionDAOImpl(@Value("${default.parameter.locale}") String locale) {
        this.locale = locale;
    }


    @Override
    public Question getQuestion(DataRow dataRow) {
        List<Answer> answers = new ArrayList<>();
        return new Question(dataRow.getNameEn(), dataRow.getNameRu(), answers);
    }

    @Override
    public String getQuestionLocalised(Question question) {
        if ("ru".equals(locale)) {
            return question.getQuestionTextRu();
        }
        return question.getQuestionTextEn();
    }
}
