package org.lessons.java.spring.connect_to_db.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
            .requestMatchers("/books/create", "/books/edit/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST , "/books/**").hasAuthority("ADMIN")
            .requestMatchers("/categories", "/categories/**").hasAuthority("ADMIN")
            .requestMatchers("/books", "/books/**").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers("/**").permitAll()
            .and().formLogin()
            .and().logout()
            .and().exceptionHandling();

        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }
    
    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        //servizio per il recupero degli utenti via username
        authProvider.setUserDetailsService(userDetailService());
        //Password encorder 
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    





}
