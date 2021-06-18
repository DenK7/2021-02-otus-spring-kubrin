package ru.otus.spring.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.batch.domain.h2.H2Author;
import ru.otus.spring.batch.domain.h2.H2Book;
import ru.otus.spring.batch.domain.h2.H2Genre;
import ru.otus.spring.batch.repositories.h2.H2AuthorRepository;
import ru.otus.spring.batch.repositories.h2.H2BookRepository;
import ru.otus.spring.batch.repositories.h2.H2GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
@DisplayName("Job для заливки данных в H2 из Mongo должен: ")
class JobConfigTest {
	public static final String IMPORT_JOB_NAME = "importBookJob";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Autowired
	private H2BookRepository bookRepository;
	@Autowired
	private H2AuthorRepository authorRepository;
	@Autowired
	private H2GenreRepository genreRepository;

	@BeforeEach
	void clearMetaData() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	@DisplayName("заливать 3 авторов, 2 жанра и 2 книги ")
	void contextLoads() throws Exception {
		Job job = jobLauncherTestUtils.getJob();
		assertThat(job).isNotNull().extracting(Job::getName).isEqualTo(IMPORT_JOB_NAME);

		JobParameters parameters = new JobParametersBuilder()
				.toJobParameters();
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(parameters);

		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

		List<H2Author> authors = authorRepository.findAll();
		assertThat(authors.size()).isEqualTo(3);
		List<H2Genre> genres = genreRepository.findAll();
		assertThat(genres.size()).isEqualTo(2);
		List<H2Book> books = bookRepository.findAll();
		assertThat(books.size()).isEqualTo(2);
	}

}
