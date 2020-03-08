
package security;

import java.security.SecureRandom;
import utils.Settings;

/* This generates a secure random per execution of the server
 * A server restart, will generate a new key, making all existing tokens invalid
 * For production (and if a load-balancer is used) come up with a persistent key strategy */
public class SharedSecret {
    private static byte[] secret;
    public static byte[] getSharedKey() {
        if (secret == null) {
            secret = System.getenv("SECRET")!=null? System.getenv("SECRET").getBytes() : Settings.getPropertyValue("local.secret").getBytes();
//            System.out.println("secret: " + secret);
            new SecureRandom().nextBytes(secret);
        }
        return secret;
    }
}