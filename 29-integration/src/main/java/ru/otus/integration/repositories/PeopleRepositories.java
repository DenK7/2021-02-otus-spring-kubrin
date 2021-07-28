package ru.otus.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.integration.entity.People;

public interface PeopleRepositories extends JpaRepository<People, Long> {
}
