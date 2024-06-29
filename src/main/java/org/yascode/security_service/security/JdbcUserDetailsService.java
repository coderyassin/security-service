package org.yascode.security_service.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yascode.security_service.entity.UserPrincipal;
import org.yascode.security_service.repository.UserPrincipalRepository;

import java.util.Objects;

@Component
public class JdbcUserDetailsService implements UserDetailsService {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final UserPrincipalRepository userPrincipalRepository;

    public JdbcUserDetailsService(UserPrincipalRepository userPrincipalRepository) {
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
}
