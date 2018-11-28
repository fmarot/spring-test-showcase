package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Goal of this dummy controller is just to take a long time to init.
 * This way it shows that starting a full AppContext in the tests is not 
 * necessarily a good idea as the tests will take a long time to be executed.
 */
@Controller
public class LongGreetingController {

    public LongGreetingController() throws InterruptedException {
    	int i=0;
        while(i<10) {
        	System.out.println("This controller is long to init...");
        	Thread.sleep(500);
        	i++;
        }
    }

    @RequestMapping("/longgreeting")
    public @ResponseBody String greeting() {
        return "hello fron LongGreetingController";
    }

}
