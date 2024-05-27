package uz.pdp.g34springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class G34SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(G34SpringBootSecurityApplication.class, args);
    }

}
