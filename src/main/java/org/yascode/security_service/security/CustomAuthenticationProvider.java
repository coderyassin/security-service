package org.yascode.security_service.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final MyUserDetailsService userDetailsService;
    private final JdbcUserDetailsService jdbcUserDetailsService;

    public CustomAuthenticationProvider(MyUserDetailsService userDetailsService,
                                        JdbcUserDetailsService jdbcUserDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jdbcUserDetailsService = jdbcUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserDetails userDetails = jdbcUserDetailsService.loadUserByUsername(username);

        if(Objects.equals(password, userDetails.getPassword()))
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

        throw new BadCredentialsException("Invalid Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
