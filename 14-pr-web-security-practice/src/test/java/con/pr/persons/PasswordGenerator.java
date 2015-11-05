package con.pr.persons;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by iuliana.cosmina on 11/3/15.
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encrypted = encoder.encodePassword("doe", "MySalt");
        System.out.println(encrypted);
        encrypted = encoder.encodePassword("admin", "MySalt");
        System.out.println(encrypted);
    }
}
