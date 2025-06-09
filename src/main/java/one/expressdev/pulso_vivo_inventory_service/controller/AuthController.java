package one.expressdev.pulso_vivo_inventory_service.controller;

import one.expressdev.pulso_vivo_inventory_service.model.AuthRequest;
import one.expressdev.pulso_vivo_inventory_service.model.AuthResponse;
import one.expressdev.pulso_vivo_inventory_service.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
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
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
