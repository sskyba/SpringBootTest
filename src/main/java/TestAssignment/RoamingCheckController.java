package TestAssignment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

@RestController
public class RoamingCheckController {

    @Value("${app.ips}")
    private String[] IPs;

    @RequestMapping(value = "/roamingCheck", method = RequestMethod.POST)
    public RoamingCheck roamingCheck(@RequestParam(value="subscriberNumber") String subscriberNumber, @RequestParam(value="ipAddress") String ipAddress) {
        return new RoamingCheck(subscriberNumber, getNetworkType(ipAddress)) ;
    }

    private String getNetworkType(String ipAddress) {
    	return Arrays.stream(IPs).anyMatch(ipAddress::equals) ? "HOME" : "ROAMING";
    }
}
