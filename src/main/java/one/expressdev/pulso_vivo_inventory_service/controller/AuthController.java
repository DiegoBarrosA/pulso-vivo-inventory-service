package one.expressdev.pulso_vivo_inventory_service.controller;

import one.expressdev.pulso_vivo_inventory_service.model.AuthRequest;
import one.expressdev.pulso_vivo_inventory_service.model.AuthResponse;
import one.expressdev.pulso_vivo_inventory_service.security.JwtUtil;
import one.expressdev.pulso_vivo_inventory_service.security.MsalTokenValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final MsalTokenValidator msalTokenValidator;

    public AuthController(JwtUtil jwtUtil, MsalTokenValidator msalTokenValidator) {
        this.jwtUtil = jwtUtil;
        this.msalTokenValidator = msalTokenValidator;
    }

    @PostMapping("/validate-msal")
    public ResponseEntity<AuthResponse> validateMsalToken(@RequestBody Map<String, String> request) {
        String msalToken = request.get("token");
        
        try {
            // Validate the MSAL token
            String username = msalTokenValidator.validateToken(msalToken);
            
            // Generate your application's JWT token
            String appToken = jwtUtil.generateToken(username);
            
            return ResponseEntity.ok(new AuthResponse(appToken, username));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @RequestBody AuthRequest request
    ) {
        // Validación simple (solo para pruebas)
        if (
            "admin".equals(request.getUsername()) &&
            "admin123".equals(request.getPassword())
        ) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token, request.getUsername()));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
