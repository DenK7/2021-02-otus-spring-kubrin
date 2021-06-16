package ru.otus.base.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.base.spring.security.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);
}
