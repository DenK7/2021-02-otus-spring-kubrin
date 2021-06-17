package ru.otus.spring.batch.Shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.batch.service.BookService;

@ShellComponent
public class ShellCommands {

    private final Job importBookJob;
    private final JobLauncher jobLauncher;
    private final BookService bookService;

    public ShellCommands(Job importBookJob, JobLauncher jobLauncher, BookService bookService) {
        this.importBookJob = importBookJob;
        this.jobLauncher = jobLauncher;
        this.bookService = bookService;
    }

    @ShellMethod(value = "start import Book from Mongo to H2", key = "sj")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importBookJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(bookService.getAllBooks());
    }

}
