package ru.apzakharov.projects.service;

import org.springframework.stereotype.Service;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.domain.State;
import ru.apzakharov.projects.domain.User;
import ru.apzakharov.projects.repositories.StateRepository;
import ru.apzakharov.projects.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final StateRepository stateRepository;

    public UserService(UserRepository userRepository, StateRepository stateRepository) {
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
    }

    public User findUserByUpdate(ClassifiedUpdate classifiedUpdate) {

        // Проверим, существует ли этот пользователь.
        if(userRepository.findByChatId(classifiedUpdate.getUserId()) != null) {
            User user = userRepository.findByChatId(classifiedUpdate.getUserId());

            // Если мы не смогли до этого записать имя пользователя, то запишем его.
            if(user.getUserName() == null && classifiedUpdate.getUserName() != null)
                user.setUserName(classifiedUpdate.getUserName());

            // Проверим менял ли пользователя имя.
            if(user.getUserName() != null)
                if (!user.getUserName().equals(classifiedUpdate.getUserName()))
                    user.setUserName(classifiedUpdate.getUserName());

            if(!user.getName().equals(classifiedUpdate.getName()))
                user.setName(classifiedUpdate.getName());

            return user;
        }
        try {
            User user = new User();
            user.setName(classifiedUpdate.getName());
            user.setPermissions(0L);
            user.setChatId(classifiedUpdate.getUserId());
            user.setUserName(classifiedUpdate.getUserName());

            State state = new State();
            state.setStateValue(null);
            state.setUser(user);

            stateRepository.save(state);

            user.setState(state);
            userRepository.save(user);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
