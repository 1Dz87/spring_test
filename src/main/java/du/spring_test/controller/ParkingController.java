package du.spring_test.controller;

import du.spring_test.model.Car;
import du.spring_test.model.User;
import du.spring_test.repository.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ParkingController {

    private final IUserDAO userDAO;

    @Autowired
    public ParkingController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/createUserView")
    public String createUserView() {
        return "createUser";
    }

    @RequestMapping("/createUser")
    public ModelAndView createUser(@RequestParam String login,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName) {
        ModelAndView modelAndView = new ModelAndView("viewUser");

        User user = new User();
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Car tesla = new Car();
        tesla.setModel("Testla");
        tesla.setUser(user);
        Car audi = new Car();
        audi.setModel("AUDI");
        audi.setUser(user);
        List<Car> cars = new ArrayList<>();
        cars.add(tesla);
        cars.add(audi);
        user.setCars(cars);

        userDAO.createUser(user);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
