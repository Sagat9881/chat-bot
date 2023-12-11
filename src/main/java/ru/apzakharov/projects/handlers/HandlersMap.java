package ru.apzakharov.projects.handlers;

import jakarta.annotation.PostConstruct;
import ru.apzakharov.projects.domain.User;
import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.dto.TelegramType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HandlersMap {
    private HashMap<TelegramType, List<Handler>> hashMap = new HashMap<>();
    private final List<Handler> handlers;

    // Тут точно также находим все обработчики, просто в первом случае я использовал
    // @Autowired. Это немного лучше.
    public HandlersMap(List<Handler> handlers) {
        this.handlers = handlers;
    }

    @PostConstruct
    private void init() {
        for(Handler handler : handlers) {
            if(!hashMap.containsKey(handler.getHandleType()))
                hashMap.put(handler.getHandleType(), new ArrayList<>());

            hashMap.get(handler.getHandleType()).add(handler);
        }

        hashMap.values().forEach(h -> h.sort(new Comparator<Handler>() {
            @Override
            public int compare(Handler o1, Handler o2) {
                return o2.priority() - o1.priority();
            }
        }));
    }

    public Answer execute(ClassifiedUpdate classifiedUpdate, User user) {
        if(!hashMap.containsKey(classifiedUpdate.getTelegramType()))
            return new Answer();

        for (Handler handler : hashMap.get(classifiedUpdate.getTelegramType())) {
            if(handler.condition(user, classifiedUpdate))
                return handler.getAnswer(user, classifiedUpdate);
        }
        return null;
    }
}
