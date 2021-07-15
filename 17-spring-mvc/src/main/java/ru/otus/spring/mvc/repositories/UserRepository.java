package ru.otus.spring.mvc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mvc.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);
}
