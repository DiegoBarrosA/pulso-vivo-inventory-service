package one.expressdev.pulso_vivo_inventory_service;

import one.expressdev.pulso_vivo_inventory_service.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PulsoVivoInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulsoVivoInventoryServiceApplication.class, args);
        // In your main method or test class
        JwtUtil jwtutil = new JwtUtil();
        String token = jwtutil.generateToken("your-username");
        System.out.println("token: " + token);
    }
}
