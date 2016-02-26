package app.testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fito on 2/24/2016.
 */
public class Tester {

    String IPADDRESS_PATTERN =
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    //Check user input ip address
    public boolean test_ip(String ip_address){

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(ip_address);

        //If ip_address matches regex return true
        if(matcher.find()){
            return true;
        }

        //If ip_address does not match regex return false
        return false;
    }

}
