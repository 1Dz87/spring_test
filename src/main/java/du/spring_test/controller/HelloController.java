package du.spring_test.controller;

import du.spring_test.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/welcome")
public class HelloController {

    private MyService myService;

    private MyService oldMyService;

    @Autowired
    public HelloController(@Qualifier("newService") MyService myService, @Qualifier("oldService") MyService oldMyService) {
        this.myService = myService;
        this.oldMyService = oldMyService;
    }

    @RequestMapping(value = "/hello")
    public ModelAndView printHello(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("hello");
        model.addObject("message", myService.getMessage());
        model.addObject("message2", oldMyService.getMessage());
        return model;
    }

    @RequestMapping(value = "/hello_post")
    public String printHelloPOST(ModelMap map) {
        map.addAttribute("message", "HELLO POST");
        return "hello";
    }

    @RequestMapping(value = "/goodbye", method = RequestMethod.GET)
    public String printGoodBye() {
        return "goodbye";
    }
}
