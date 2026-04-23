package com.example.FullStack_26_Back.Config;

import com.example.FullStack_26_Back.Modelo.User;
import com.example.FullStack_26_Back.Repositorio.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Se ejecuta automáticamente cada vez que arranca el back.
 * Crea el admin por defecto si aún no existe en la base de datos.
 *
 * Credenciales quemadas en el front hasta el momento(main.js):
 *   Email    → sxmxel05@gmail.com
 *   Password → cesde2026
 */
@Component
public class AdminInitializer implements ApplicationRunner {

    private static final String ADMIN_EMAIL    = "sxmxel05@gmail.com";
    private static final String ADMIN_PASSWORD = "cesde2026";
    private static final String ADMIN_NAME     = "Administrador ESPRIT";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdminInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Solo crea el admin si no existe todavía
        if (!userRepository.existsByEmail(ADMIN_EMAIL)) {
            User admin = new User(
                    ADMIN_NAME,
                    ADMIN_EMAIL,
                    passwordEncoder.encode(ADMIN_PASSWORD),
                    User.Role.ADMIN
            );
            userRepository.save(admin);
            System.out.println("✅ Admin por defecto creado: " + ADMIN_EMAIL);
        } else {
            System.out.println("ℹ️  Admin ya existe, no se crea de nuevo.");
        }
    }
}