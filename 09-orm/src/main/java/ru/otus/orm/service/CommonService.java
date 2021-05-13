package ru.otus.orm.service;

import ru.otus.orm.exception.InputNotCorrectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CommonService {

    private CommonService () {
    }

    public static String getValue(String message) {
        System.out.println(message);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String value = reader.readLine();
            if (value.trim().length() == 0) {
                throw new InputNotCorrectException();
            }
            return value;
        } catch (IOException e) {
            throw new InputNotCorrectException();
        }
    }

}
