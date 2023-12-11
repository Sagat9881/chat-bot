package ru.apzakharov.projects.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.apzakharov.projects.domain.State;
import ru.apzakharov.projects.domain.User;

public interface StateRepository extends CrudRepository<State, Long> {
}
