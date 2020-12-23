package du.spring_test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MessageDAO implements DAO {

    private final Map<String, String> dataBase;

    @Autowired
    public MessageDAO(Map<String, String> dataBase) {
        this.dataBase = dataBase;
    }

    public String getMessage(String key) {
        String result = dataBase.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Не найдено занчение по заданному ключу");
        }
        return result;
    }
}
