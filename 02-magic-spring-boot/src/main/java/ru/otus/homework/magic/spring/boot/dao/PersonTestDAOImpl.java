package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.cvs.CSVWork;
import ru.otus.homework.magic.spring.boot.cvs.CVSRow;
import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("PersonTestDAO")
public class PersonTestDAOImpl implements PersonTestDAO{
    private final CSVWork csvWork;

    @Value("${number.correct.answers}")
    private Integer numberCorrectAnswers;

    public PersonTestDAOImpl(@Qualifier("csvWork") CSVWork workCSV) {
        this.csvWork = workCSV;
    }

    @Override
    public PersonTest getPersonTest() throws IOException {
        List<CVSRow> rows = csvWork.getCSVRows();

        if (rows == null || rows.size() == 0) {
            return null;
        }
        List<Question> questions = new ArrayList<>();

        List<Answer> answers = null;
        for (CVSRow csvRow:rows) {
            if ("?".equals(csvRow.getTypeRow())) {
                answers = new ArrayList<>();
                Question question = new Question(csvRow.getDataRow(), answers);
                questions.add(question);
            } else {
                if (answers != null) {
                    Answer answer = Answer.builder()
                            .isCorrect(csvRow.getTypeRow())
                            .answerText(csvRow.getDataRow())
                            .build();
                    answers.add(answer);
                }
            }
        }

        return new PersonTest(questions, numberCorrectAnswers, 0);
    }
}
