package app.store.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/helloworld")
public class HelloWorld {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    @ResponseBody
    public String getHello() {
        return "hey dude";
    }

}
