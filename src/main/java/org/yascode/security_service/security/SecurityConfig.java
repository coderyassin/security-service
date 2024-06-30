package org.yascode.security_service.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthenticationEntryPoint authEntryPoint;

    public SecurityConfig(@Qualifier("customAuthenticationEntryPoint") AuthenticationEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
                        requests.requestMatchers("/", "/home").permitAll().
                                requestMatchers("/home/*").hasAnyAuthority("USER", "ADMIN").
                                requestMatchers("/statistic/*").hasAuthority("ADMIN").
                                requestMatchers("/userPrincipal/*", "/authority/*").hasAuthority("SUPER_ADMIN").
                                anyRequest().authenticated()).
                                httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint)).
                                csrf(AbstractHttpConfigurer::disable).
                                exceptionHandling(Customizer.withDefaults());

        return http.build();
    }
}
