package du.spring_test.service;

import du.spring_test.lib.exceptions.UserNotFoundException;
import du.spring_test.model.User;

public interface IUserService {

    User findUserByLogin(String login, boolean initLazyObjects) throws UserNotFoundException;
}
