package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.parser.DataRow;

@Component("AnswerDAO")
public class AnswerDAOImpl implements AnswerDAO{
    private final String locale;

    public AnswerDAOImpl(@Value("${default.parameter.locale}") String locale) {
        this.locale = locale;
    }
    @Override
    public Answer getAnswer(DataRow dataRow) {
        if (dataRow == null) {
            return null;
        }

        return Answer.builder()
                .isCorrect(dataRow.getTypeRow())
                .answerTextRu(dataRow.getNameEn())
                .build();
    }

    @Override
    public String getAnswerLocalised(Answer answer) {
        if ("ru".equals(locale)) {
            return answer.getAnswerTextRu();
        }
        return answer.getAnswerTextEn();
    }
}
