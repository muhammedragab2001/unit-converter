/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.moh.converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;


/**
 *
 * @author moh
 */
@Configuration // تعلن أن هذه الفئة تحتوي على تهيئة Spring
@EnableWebSecurity // تمكّن تهيئة أمان الويب في Spring
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // اسمح بالوصول لجميع الطلبات دون مصادقة
            )
            .csrf(csrf -> csrf.disable()) // قم بتعطيل CSRF لتبسيط المثال
            .headers(headers -> headers
                .contentTypeOptions(contentTypeOptions -> {}) // X-Content-Type-Options: nosniff
                .frameOptions(frameOptions -> frameOptions.deny()) // X-Frame-Options: DENY
                // **هنا نقوم بإضافة رأس X-XSS-Protection بطريقة أكثر عمومية**
                .addHeaderWriter(new StaticHeadersWriter("X-XSS-Protection", "1; mode=block"))
                // يمكنك أيضًا إضافة CSP هنا إذا أردت لاحقًا
                // .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
            );
        return http.build();
    }

}
