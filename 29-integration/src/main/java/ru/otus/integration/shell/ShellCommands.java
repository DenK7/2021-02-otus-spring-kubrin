package ru.otus.integration.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {

    @ShellMethod(value = "Start process", key = {"sp", "start-process"})
    public String startIntegrationProcess() {
        return null;
    }
}
