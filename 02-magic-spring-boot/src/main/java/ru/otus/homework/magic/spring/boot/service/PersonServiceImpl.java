package ru.otus.homework.magic.spring.boot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.homework.magic.spring.boot.dao.AnswerDAO;
import ru.otus.homework.magic.spring.boot.dao.PersonDAO;
import ru.otus.homework.magic.spring.boot.dao.PersonTestDAO;
import ru.otus.homework.magic.spring.boot.dao.QuestionDAO;
import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.domain.Question;
import ru.otus.homework.magic.spring.boot.exception.PersonNotFound;
import ru.otus.homework.magic.spring.boot.messages.MessageGenerate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonDAO personDAO;
    private final PersonTestDAO personTestDAO;
    private final MessageGenerate messageGenerate;
    private final QuestionDAO questionDAO;
    private final AnswerDAO answerDAO;

    public PersonServiceImpl(@Qualifier("PersonDAO") PersonDAO personDAO
            , @Qualifier("PersonTestDAO") PersonTestDAO personTestDAO
            , MessageGenerate messageGenerate, QuestionDAO questionDAO, AnswerDAO answerDAO) {
        this.personDAO = personDAO;
        this.personTestDAO = personTestDAO;
        this.messageGenerate = messageGenerate;
        this.questionDAO = questionDAO;
        this.answerDAO = answerDAO;
    }

    @Override
    public Person loadQuestionIntoPerson(String lastName, String firstName) {
        Person person = personDAO.getPerson(lastName, firstName);
        PersonTest personTest = personTestDAO.getPersonTest();
        personDAO.getPersonWithQuestions(person, personTest);
        return person;
    }

    @Override
    public void startTest(Person person) {
        if (person == null || person.getTest() == null) {
            throw new PersonNotFound();
        }
        for (int i = 0; i < 5; i++) {
            if (getTestQuestion(person.getTest().getQuestions().get(i))) {
                person.getTest().setCorrectAnswers(person.getTest().getCorrectAnswers() + 1);
            }
        }
        printResult(person.getTest());
    }

    private boolean getTestQuestion(Question question) {
        System.out.println(messageGenerate.getMessage("QUESTION")+ questionDAO.getQuestionLocalised(question));
        int i = 0;
        for (Answer answer: question.getAnswers()) {
            i++;
            System.out.println(i + ") " + answerDAO.getAnswerLocalised(answer));
        }
        System.out.println(messageGenerate.getMessage("ENTER.NUMBER.CORRECT.ANSWER"));

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String answerNumber = reader.readLine();
            if ("+".equals(question.getAnswers().get(Integer.parseInt(answerNumber)-1).getIsCorrect())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private void printResult (PersonTest personTest) {
        System.out.println(messageGenerate.getMessage("NUMBER.CORRECT.ANSWERS") + personTest.getCorrectAnswers());
        if (personTest.getCorrectAnswers() >= personTest.getMinNumberCorrectAnswers()) {
            System.out.println(messageGenerate.getMessage("TEST.PASSED"));
        } else {
            System.out.println(messageGenerate.getMessage("TEST.FAILED"));
        }
    }

}
