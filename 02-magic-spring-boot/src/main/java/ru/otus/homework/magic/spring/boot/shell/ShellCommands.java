package ru.otus.homework.magic.spring.boot.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.*;
import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.messages.MessageGenerate;
import ru.otus.homework.magic.spring.boot.service.PersonService;

@ShellComponent
public class ShellCommands {

    private final MessageGenerate messageGenerate;
    private final PersonService personService;

    public ShellCommands(MessageGenerate messageGenerate, PersonService personService) {
        this.messageGenerate = messageGenerate;
        this.personService = personService;
    }

    private String lastName;
    private String firstName;
    private Person person;

    @ShellMethod(value = "Enter last name", key = {"l", "last-name"})
    public String enterLastName(@ShellOption(defaultValue = "Petrov") String lastName) {
        this.lastName = lastName;
        return lastName;
    }

    @ShellMethod(value = "Enter first name", key = {"f", "first-name"})
    @ShellMethodAvailability(value = "isLastNameAvailability")
    public String enterFirstName(@ShellOption(defaultValue = "Petr") String firstName) {
        this.firstName = firstName;
        this.person = personService.loadQuestionIntoPerson(this.lastName, this.firstName);
        return firstName;
    }

    @ShellMethod(value = "Start test", key = {"s", "start-test"})
    @ShellMethodAvailability(value = "isFirstNameAvailability")
    public String startTest(@ShellOption(defaultValue = "End test") String endTest) {
        personService.startTest(person);
        return endTest;
    }


    private Availability isLastNameAvailability() {
        return lastName == null? Availability.unavailable(messageGenerate.getMessage("LAST.NAME.NOT.ENTER")): Availability.available();
    }

    private Availability isFirstNameAvailability() {
        return firstName == null? Availability.unavailable(messageGenerate.getMessage("FIRST.NAME.NOT.ENTER")): Availability.available();
    }


}
