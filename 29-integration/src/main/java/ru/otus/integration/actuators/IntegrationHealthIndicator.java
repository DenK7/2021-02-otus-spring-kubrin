package ru.otus.integration.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class IntegrationHealthIndicator implements HealthIndicator {

    private JdbcTemplate jdbcTemplate;

    public IntegrationHealthIndicator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    //http://localhost:8080/actuator/health
    // проверка что бд доступна и в таблице есть данные для запуска интеграции
    public Health health() {
        try{
            Long cnt = jdbcTemplate.queryForObject("select count(*) cnt from PEOPLE", Long.class);
            if (cnt.equals(0L)) {
                return Health.down()
                        .status(Status.DOWN)
                        .withDetail("message", "Database is not ready to work")
                        .build();
            }
            return Health.up().withDetail("message", "Database is work and ready to work").build();
        } catch (Exception e) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Database is down")
                    .build();

        }
    }
}
