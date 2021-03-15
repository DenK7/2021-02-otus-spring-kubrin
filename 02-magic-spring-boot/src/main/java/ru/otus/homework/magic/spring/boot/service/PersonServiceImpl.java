package ru.otus.homework.magic.spring.boot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.homework.magic.spring.boot.dao.PersonDAO;
import ru.otus.homework.magic.spring.boot.dao.PersonTestDAO;
import ru.otus.homework.magic.spring.boot.domain.Answer;
import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonDAO personDAO;
    private final PersonTestDAO personTestDAO;

    public PersonServiceImpl(@Qualifier("PersonDAO") PersonDAO personDAO
            , @Qualifier("PersonTestDAO") PersonTestDAO personTestDAO) {
        this.personDAO = personDAO;
        this.personTestDAO = personTestDAO;
    }

    @Override
    public void startTest() throws IOException {
        PersonTest personTest = personTestDAO.getPersonTest();
        Person person = personDAO.getPersonByName(personTest);
        for (int i = 0; i < 5; i++) {
            if (getTestQuestion(person.getTest().getQuestions().get(i))) {
                person.getTest().setCorrectAnswers(person.getTest().getCorrectAnswers() + 1);
            }
        }
        printResult(personTest);
    }

    private boolean getTestQuestion(Question question) throws IOException {
        System.out.println("Question :" + question.getQuestionText());
        int i = 0;
        for (Answer answer: question.getAnswers()) {
            i++;
            System.out.println(i + ") " + answer.getAnswerText());
        }
        System.out.println("Enter the number of the correct answer:");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String answerNumber = reader.readLine();

        try {
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
        System.out.println("Number of correct answers: " + personTest.getCorrectAnswers());
        if (personTest.getCorrectAnswers() >= personTest.getMinNumberCorrectAnswers()) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }

}
