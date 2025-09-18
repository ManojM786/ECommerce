package com.e_commerce.e_commerce;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.model.Role;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserLoader implements CommandLineRunner {

    @Autowired
    private UserManagementDAO userManagementDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@gmail.com";
        if (userManagementDAO.findByEmail(adminEmail).isEmpty()) {
            UserData admin = new UserData();
            admin.setUserName("admin");
            admin.setPassWord(passwordEncoder.encode("admin123"));
            admin.setUserRole(Role.ADMIN);
            admin.setEmail(adminEmail);
            userManagementDAO.save(admin);
            System.out.println("Admin user created: " + adminEmail);
        }
    }
}
