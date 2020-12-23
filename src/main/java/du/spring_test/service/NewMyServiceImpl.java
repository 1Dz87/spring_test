package du.spring_test.service;

import du.spring_test.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newService")
public class NewMyServiceImpl implements MyService {

    private final DAO dao;

    @Autowired
    public NewMyServiceImpl(DAO dao) {
        this.dao = dao;
    }

    public String getMessage() {
        return dao.getMessage("eng");
    }
}
