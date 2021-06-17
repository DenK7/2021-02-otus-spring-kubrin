package ru.otus.spring.batch.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.lang.NonNull;
import ru.otus.spring.batch.domain.h2.H2Author;
import ru.otus.spring.batch.domain.h2.H2Book;
import ru.otus.spring.batch.domain.h2.H2Genre;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;
import ru.otus.spring.batch.domain.mongo.MongoBook;
import ru.otus.spring.batch.domain.mongo.MongoGenre;
import ru.otus.spring.batch.service.AuthorServiceImpl;
import ru.otus.spring.batch.service.BookService;
import ru.otus.spring.batch.service.GenreServiceImpl;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class JobConfig {
    public static final String IMPORT_JOB_NAME = "importBookJob";

    private final Logger logger = LoggerFactory.getLogger("Spring Batch");

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importBookJob (Step transformGenre, Step transformAuthor
            , Step transformBook) {
        return jobBuilderFactory.get(IMPORT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformGenre)
                .next(transformAuthor)
                .next(transformBook)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    //--------------------------------------- book ----------------------------------
    @Bean
    public Step transformBook (ItemReader<MongoBook> reader, ItemWriter<H2Book> writer,
                               ItemProcessor<MongoBook, H2Book> processor) {
        return stepBuilderFactory.get("stepBook")
                .<MongoBook, H2Book>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(@NonNull MongoBook o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<MongoBook, H2Book> processorBook(BookService bookService) {
        return bookService::mapBook;
    }

    @StepScope
    @Bean
    public MongoItemReader<MongoBook> readerBook(MongoTemplate template) {
        return new MongoItemReaderBuilder<MongoBook>()
                .name("MongoItemReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(MongoBook.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<H2Book> writerBook(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<H2Book>()
                .itemSqlParameterSourceProvider(item -> {
                    final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
                    sqlParameterSource.addValue("bookName", item.getBookName());
                    sqlParameterSource.addValue("mongoId", item.getMongoId());
                    sqlParameterSource.addValue("genreId", item.getH2Genre().getId());
                    return sqlParameterSource;
                })
                .sql("insert into book (book_name, mongo_id, genre_id) values (:bookName, :mongoId, :genreId)")
                .dataSource(dataSource)
                .build();
    }

    //--------------------------------------- genre ----------------------------------
    @Bean
    public Step transformGenre (ItemReader<MongoGenre> reader, ItemWriter<H2Genre> writer,
                               ItemProcessor<MongoGenre, H2Genre> processor) {
        return stepBuilderFactory.get("stepGenre")
                .<MongoGenre, H2Genre>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения жанров");
                    }

                    public void afterRead(@NonNull MongoGenre o) {
                        logger.info("Конец чтения жанров");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения жанров");
                    }
                })
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<MongoGenre> readerGenre(MongoTemplate template) {
        return new MongoItemReaderBuilder<MongoGenre>()
                .name("readerGenre")
                .template(template)
                .jsonQuery("{}")
                .targetType(MongoGenre.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<MongoGenre, H2Genre> processorGenre(GenreServiceImpl genreService) {
        return genreService::mapGenre;
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<H2Genre> writerGenre(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<H2Genre>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into genre (genre_name, mongo_id) values (:genreName, :mongoId)")
                .dataSource(dataSource)
                .build();
    }

    //--------------------------------------- author ----------------------------------
    @Bean
    public Step transformAuthor (ItemReader<MongoAuthor> reader, ItemWriter<H2Author> writer,
                                 ItemProcessor<MongoAuthor, H2Author> processor) {
        return stepBuilderFactory.get("stepAuthor")
                .<MongoAuthor, H2Author>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения авторов");
                    }

                    public void afterRead(@NonNull MongoAuthor o) {
                        logger.info("Конец чтения авторов");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения авторов");
                    }
                })
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<MongoAuthor> readerAuthor(MongoTemplate template) {
        return new MongoItemReaderBuilder<MongoAuthor>()
                .name("readerAuthor")
                .template(template)
                .jsonQuery("{}")
                .targetType(MongoAuthor.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<MongoAuthor, H2Author> processorAuthor(AuthorServiceImpl authorService) {
        return authorService::mapAuthor;
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<H2Author> writerAuthor(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<H2Author>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into author (author_name, mongo_id) values (:authorName, :mongoId)")
                .dataSource(dataSource)
                .build();
    }

}
