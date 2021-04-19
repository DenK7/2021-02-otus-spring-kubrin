package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.exception.LoadFileException;
import ru.otus.homework.magic.spring.boot.exception.ParsException;
import ru.otus.homework.magic.spring.boot.loader.Loader;
import ru.otus.homework.magic.spring.boot.parser.Parser;
import ru.otus.homework.magic.spring.boot.parser.DataRow;
import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Component("PersonTestDAO")
public class PersonTestDAOImpl implements PersonTestDAO{
    private final Parser parser;
    private final Loader loader;
    private final AnswerDAO answerDAO;
    private final QuestionDAO questionDAO;

    private final Integer numberCorrectAnswers;

    public PersonTestDAOImpl(Parser parser, Loader loader
            , AnswerDAO answerDAO, QuestionDAO questionDAO, @Value("${number.correct.answers}") Integer numberCorrectAnswers) {
        this.parser = parser;
        this.loader = loader;
        this.answerDAO = answerDAO;
        this.questionDAO = questionDAO;
        this.numberCorrectAnswers = numberCorrectAnswers;
    }

    @Override
    public PersonTest getPersonTest() throws LoadFileException, ParsException {
        List<DataRow> rows = parser.getParsRows(loader.loadFile());

        if (rows == null || rows.size() == 0) {
            return null;
        }
        List<Question> questions = new ArrayList<>();

        List<Answer> answers = null;
        for (DataRow dataRow :rows) {
            if ("?".equals(dataRow.getTypeRow())) {
                Question question = questionDAO.getQuestion(dataRow);
                answers = question.getAnswers();
                questions.add(question);
            } else {
                if (answers != null) {
                    answers.add(answerDAO.getAnswer(dataRow));
                }
            }
        }

        return new PersonTest(questions, numberCorrectAnswers, 0);
    }
}
