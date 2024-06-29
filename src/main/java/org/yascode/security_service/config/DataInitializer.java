package org.yascode.security_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yascode.security_service.entity.Authority;
import org.yascode.security_service.entity.UserPrincipal;
import org.yascode.security_service.repository.AuthorityRepository;
import org.yascode.security_service.repository.UserPrincipalRepository;
import org.yascode.security_service.security.JdbcUserDetailsService;

import java.util.Set;

//@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(UserPrincipalRepository userPrincipalRepository, AuthorityRepository authorityRepository) {
        return args -> {
            Authority authority1 = new Authority();
            authority1.setAuthority("USER");
            authority1 = authorityRepository.save(authority1);

            Authority authority2 = new Authority();
            authority2.setAuthority("ADMIN");
            authority2 = authorityRepository.save(authority2);

            UserPrincipal user1 = new UserPrincipal();
            user1.setUsername("073176");
            user1.setPassword("YassMel2197@@@@%%%%****5");
            user1.setAuthorities(Set.of(authority1, authority2));
            UserPrincipal user1Saved = userPrincipalRepository.save(user1);

            UserPrincipal user2 = new UserPrincipal();
            user2.setUsername("078171");
            user2.setPassword("AkraMag2120@@@@%%%%****5");
            user2.setAuthorities(Set.of(authority1));

            UserPrincipal user2Saved = userPrincipalRepository.save(user2);

        };
    }
}
