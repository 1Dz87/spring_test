package du.spring_test.controller;

import du.spring_test.lib.exceptions.RequiredFieldException;
import du.spring_test.lib.exceptions.UserNotFoundException;
import du.spring_test.model.Parking;
import du.spring_test.model.User;
import du.spring_test.service.IParkingService;
import du.spring_test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParkingController {

    private final IUserService userService;

    private final IParkingService parkingService;

    @Autowired
    public ParkingController(IUserService userService, IParkingService parkingService) {
        this.userService = userService;
        this.parkingService = parkingService;
    }

    @RequestMapping("/")
    public String loginView() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String login, @RequestParam String password) {
        ModelAndView mv;
        try {
            User user = userService.findUserByLogin(login, Boolean.TRUE);
            mv = new ModelAndView("user");
            mv.addObject("user", user);
        } catch (UserNotFoundException e) {
            mv = new ModelAndView("login");
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/createParking", method = RequestMethod.GET)
    public String createParkingView() {
        return "createParking";
    }

    @RequestMapping(value = "/createParking", method = RequestMethod.POST)
    public ModelAndView createParking(@RequestParam String parkingName, @RequestParam(name = "NORTH", required = false) String north,
                                      @RequestParam(name = "EAST", required = false)String east,
                                      @RequestParam(name = "WEST", required = false)String west,
                                      @RequestParam(name = "SOUTH", required = false)String south,
                                      @RequestParam(name = "EAST_places", required = false)Integer ePlaces,
                                      @RequestParam(name = "NORTH_places", required = false)Integer nPlaces,
                                      @RequestParam(name = "WEST_places", required = false)Integer wPlaces,
                                      @RequestParam(name = "SOUTH_places", required = false)Integer sPlaces) {
        try {
            //TODO: create jsp for parking view
            ModelAndView view = new ModelAndView("createParking");
            Parking parking = parkingService.createParking(parkingName, north, east, west, south, ePlaces, nPlaces, wPlaces, sPlaces);
            view.addObject("parking", parking);
            return view;
        } catch (RequiredFieldException e) {
            ModelAndView view = new ModelAndView("createParking");
            view.addObject("error", e.getMessage());
            return view;
        }
    }
}
