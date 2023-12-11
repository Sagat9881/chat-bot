package ru.apzakharov.projects.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.apzakharov.projects.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByChatId(Long id);
}
