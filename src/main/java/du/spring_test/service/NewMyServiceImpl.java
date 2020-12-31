package du.spring_test.service;

import du.spring_test.repository.IMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newService")
public class NewMyServiceImpl implements MyService {

    private final IMessageDAO IMessageDao;

    @Autowired
    public NewMyServiceImpl(IMessageDAO IMessageDao) {
        this.IMessageDao = IMessageDao;
    }

    public String getMessage() {
        return IMessageDao.getMessage("eng");
    }
}
