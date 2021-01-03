package du.spring_test.repository;

import du.spring_test.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    void createUser(User user);

    User findById(Long name);

    User getCarOwner(String model);

    Optional<User> getUserByLogin(String login, boolean initLazyObjects);
}
