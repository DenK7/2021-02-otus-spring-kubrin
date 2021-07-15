package ru.otus.web.flux.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongockConfig {
    private final String databaseName;
    private final String databasePort;

    public MongockConfig(@Value("${spring.data.mongodb.database}") String databaseName,
                         @Value("${spring.data.mongodb.port}") String databasePort) {
        this.databaseName = databaseName;
        this.databasePort = databasePort;
    }

    /*
    * https://www.mongock.io/reactive
    * исходя из текущей документации, нужно подгружать для монгока MongoTemplate бин
    * из не реактивной библиотеки, что крайне не удобно, если появится реактивный монгок - убрать
    */
    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:"+databasePort+"/"+databaseName);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "library");
    }
}
