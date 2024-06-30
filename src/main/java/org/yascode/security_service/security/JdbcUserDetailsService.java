package org.yascode.security_service.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.yascode.security_service.entity.UserPrincipal;
import org.yascode.security_service.repository.UserPrincipalRepository;

import java.util.Collection;
import java.util.Objects;

@Component
public class JdbcUserDetailsService implements UserDetailsService {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final PasswordEncoder passwordEncoder;
    private final UserPrincipalRepository userPrincipalRepository;

    public JdbcUserDetailsService(@Qualifier("bCryptPasswordEncoder") PasswordEncoder passwordEncoder,
                                  UserPrincipalRepository userPrincipalRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userPrincipalRepository = userPrincipalRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = this.userPrincipalRepository.findByUsername(username);

        if (Objects.isNull(userPrincipal)) {
            this.logger.debug("Query returned no results for user '" + username + "'");
            throw new UsernameNotFoundException("Username {0} not found");
        }
        return UserDetailsPrincipal.builder()
                            .userPrincipal(userPrincipal)
                            .build();
    }

    public UserPrincipal createUser(final UserDetailsPrincipal user) {
        this.validateUserDetails(user);
        UserPrincipal userPrincipal = user.getUserPrincipal();
        userPrincipal.setPassword(this.passwordEncoder.encode(userPrincipal.getPassword()));
        return this.userPrincipalRepository.save(userPrincipal);
    }

    private void validateUserDetails(UserDetailsPrincipal user) {
        Assert.hasText(user.getUsername(), "Username may not be empty or null");
        this.validateAuthorities(user.getAuthorities());
    }

    private void validateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Authorities list must not be null");
        Assert.notEmpty(authorities, "Authorities list must not be empty");
        authorities.forEach(authority -> {
            Assert.notNull(authority, "Authorities list contains a null entry");
            Assert.hasText(authority.getAuthority(), "getAuthority() method must return a non-empty string");
        });
    }
}
