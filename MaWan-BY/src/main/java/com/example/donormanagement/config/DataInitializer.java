package com.example.donormanagement.config;

import com.example.donormanagement.entity.Role;
import com.example.donormanagement.entity.User;
import com.example.donormanagement.repository.RoleRepository;
import com.example.donormanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * Initializes baseline roles and demo user passwords on application startup.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository,
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            initRoles(roleRepository);
            initAdminUser(roleRepository, userRepository, passwordEncoder);
            initDemoUserPasswords(userRepository, passwordEncoder);
        };
    }

    private void initRoles(RoleRepository roleRepository) {
        String[] roleNames = {
            "ROLE_ADMIN",
            "ROLE_DOCTOR",
            "ROLE_LAB_TECH",
            "ROLE_REGISTRAR",
            "ROLE_REPORT_VIEWER",
            "ROLE_DONOR"
        };

        String[] roleDescriptions = {
            "System Administrator",
            "Medical Doctor",
            "Laboratory Technician",
            "Registration Staff",
            "Report Viewer",
            "Donor (self-service portal)"
        };

        for (int i = 0; i < roleNames.length; i++) {
            if (roleRepository.findByName(roleNames[i]).isEmpty()) {
                Role role = new Role();
                role.setName(roleNames[i]);
                role.setDescription(roleDescriptions[i]);
                roleRepository.save(role);
                System.out.println("Created role: " + roleNames[i]);
            }
        }
    }

    private void initAdminUser(RoleRepository roleRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPasswordHash(passwordEncoder.encode("admin123"));
            admin.setFullName("System Administrator");
            admin.setEmail("admin@example.com");
            admin.setEnabled(true);

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            admin.setRoles(roles);

            userRepository.save(admin);
            System.out.println("Created admin user with password: admin123");
        } else {
            User admin = userRepository.findByUsername("admin").get();
            admin.setPasswordHash(passwordEncoder.encode("admin123"));
            userRepository.save(admin);
            System.out.println("Updated admin user password");
        }
    }

    private void initDemoUserPasswords(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        String[] demoUsernames = {
            "doctor_wang",
            "lab_zhang",
            "registrar_liu",
            "viewer_chen",
            "donor_demo"
        };

        String demoPasswordHash = passwordEncoder.encode("password");
        for (String username : demoUsernames) {
            userRepository.findByUsername(username).ifPresent(user -> {
                user.setPasswordHash(demoPasswordHash);
                userRepository.save(user);
            });
        }
    }
}
