package du.spring_test.repository.impl;

import du.spring_test.model.Car;
import du.spring_test.model.User;
import du.spring_test.repository.IUserDAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public class UserDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager manager;

    private Session getSession() {
        return manager.unwrap(Session.class);
    }

    @Override
    public void createUser(User user) {
        getSession().saveOrUpdate(user);
    }



    @Override
    public User findById(Long id) {
        User user = manager.find(User.class, id);
        initLazyCars(Collections.singletonList(user));
        return user;
    }

    @Override
    public User getCarOwner(String model) {
        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u where u.id = (select c.user.id FROM Car c where c.model='" + model + "')", User.class);
        List<User> users = query.getResultList();
        initLazyCars(users);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Optional<User> getUserByLogin(String login, boolean initLazyObjects) {
        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u where u.login='" + login + "'", User.class);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return Optional.empty();
        }
        if (initLazyObjects) {
            initLazyCars(users);
        }
        return Optional.of(users.get(0));
    }

    private void initLazyCars(List<User> users) {
        for (User user : users) {
            for (Car car : user.getCars()) {
                Hibernate.initialize(car);
            }
        }
    }
}
