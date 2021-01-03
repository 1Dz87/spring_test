package du.spring_test.service;

import du.spring_test.lib.exceptions.RequiredFieldException;
import du.spring_test.model.Parking;
import org.springframework.web.bind.annotation.RequestParam;

public interface IParkingService {

    Parking createParking(String parkingName, String north, String east, String west, String south, Integer ePlaces,
                          Integer nPlaces, Integer wPlaces, Integer sPlaces) throws RequiredFieldException;
}
