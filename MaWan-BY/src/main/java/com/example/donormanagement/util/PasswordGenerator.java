package com.example.donormanagement.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码生成工具
 * 用于生成 BCrypt 密码哈希
 */
public class PasswordGenerator {

    /**
     * 生成管理员密码的 BCrypt 哈希
     * 
     * @param password 明文密码
     * @return BCrypt 哈希字符串
     */
    public static String generateHash(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /**
     * 生成默认管理员密码（admin123）的哈希并打印 SQL 更新语句
     */
    public static void generateAdminPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123";
        String hash = encoder.encode(password);

        System.out.println("Password: " + password);
        System.out.println("BCrypt Hash: " + hash);
        System.out.println("\nSQL Update Statement:");
        System.out.println("UPDATE users SET password_hash = '" + hash + "' WHERE username = 'admin';");
    }
}
