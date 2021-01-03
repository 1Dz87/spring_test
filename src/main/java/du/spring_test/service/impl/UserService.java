package du.spring_test.service.impl;

import du.spring_test.lib.exceptions.UserNotFoundException;
import du.spring_test.model.User;
import du.spring_test.repository.IUserDAO;
import du.spring_test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findUserByLogin(String login, boolean initLazyObjects) throws UserNotFoundException{
        Optional<User> opt = userDAO.getUserByLogin(login, initLazyObjects);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new UserNotFoundException("Пользователь с логином " + login + " не найден");
        }
    }

}
