package du.spring_test.repository.impl;

import du.spring_test.model.User;
import du.spring_test.repository.IUserDAO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public class UserDAO implements IUserDAO {

    private Session session;

    @Autowired
    public UserDAO(Session session) {
        this.session = session;
    }

    @Transactional
    @Override
    public void createUser(User user) {
        session.saveOrUpdate(user);
    }
}
