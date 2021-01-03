package du.spring_test.repository.impl;

import du.spring_test.model.Parking;
import du.spring_test.repository.IParkingDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ParkingDAO implements IParkingDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void save(Parking parking) {
        manager.persist(parking);
    }
}
