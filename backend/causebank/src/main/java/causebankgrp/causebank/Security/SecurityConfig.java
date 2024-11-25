package causebankgrp.causebank.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
                    // to enter some endpoint without authentication
                    .requestMatchers("/").permitAll()
                    /////////////////////////////////////

                    // to deny a endpoint (make it forbidden 403 )
                    .requestMatchers("/admin").denyAll()
                    ////////////////////////////////////////////////

                    .anyRequest()).authenticated();



        });
        // if you want a form to appear to login uncomment the line below
        //http.formLogin(Customizer.withDefaults());

        // to disable csrf
        http.csrf(AbstractHttpConfigurer::disable);
        /////////////////////////////

        // to make it more secure and hide the token that we receive and the detail of the request
        http.sessionManagement((session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)));

        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }
}