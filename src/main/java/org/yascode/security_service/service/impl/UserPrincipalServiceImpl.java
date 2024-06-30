package org.yascode.security_service.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.security_service.controller.request.AddAuthoritiesForUserPrincipal;
import org.yascode.security_service.controller.request.UserWithAuthorities;
import org.yascode.security_service.entity.Authority;
import org.yascode.security_service.entity.UserPrincipal;
import org.yascode.security_service.repository.AuthorityRepository;
import org.yascode.security_service.repository.UserPrincipalRepository;
import org.yascode.security_service.security.JdbcUserDetailsService;
import org.yascode.security_service.security.UserDetailsPrincipal;
import org.yascode.security_service.service.UserPrincipalService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserPrincipalServiceImpl implements UserPrincipalService {
    private final JdbcUserDetailsService jdbcUserDetailsService;
    private final AuthorityRepository authorityRepository;
    private final UserPrincipalRepository userPrincipalRepository;

    public UserPrincipalServiceImpl(JdbcUserDetailsService jdbcUserDetailsService,
                                    AuthorityRepository authorityRepository,
                                    UserPrincipalRepository userPrincipalRepository) {
        this.jdbcUserDetailsService = jdbcUserDetailsService;
        this.authorityRepository = authorityRepository;
        this.userPrincipalRepository = userPrincipalRepository;
    }

    @Override
    public UserPrincipal createUserPrincipal(UserWithAuthorities userWithAuthorities) {
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .username(userWithAuthorities.getUsername())
                .password(userWithAuthorities.getPassword())
                .enabled(userWithAuthorities.getEnableUser())
                .build();
        if(Objects.nonNull(userWithAuthorities.getAuthorities())) {
            List<Authority> authorities = authorityRepository.findByAuthorities(userWithAuthorities.getAuthorities());
            Optional.ofNullable(authorities)
                    .ifPresent(authoritiesList -> userPrincipal.setAuthorities(new HashSet<>(authoritiesList)));
        }
        UserDetailsPrincipal userDetailsPrincipal = UserDetailsPrincipal.builder()
                .userPrincipal(userPrincipal)
                .build();
        return jdbcUserDetailsService.createUser(userDetailsPrincipal);
    }

    @Override
    public UserPrincipal addAuthoritiesForUserPrincipal(AddAuthoritiesForUserPrincipal addAuthoritiesForUserPrincipal) {
        UserPrincipal userPrincipal = userPrincipalRepository.findByUsername(addAuthoritiesForUserPrincipal.getUsername());
        AtomicReference<UserPrincipal> userPrincipalSaved = new AtomicReference<>();
        if(Objects.nonNull(userPrincipal) && Objects.nonNull(addAuthoritiesForUserPrincipal.getAuthority())) {
            List<Authority> authorities = authorityRepository.findByAuthorities(addAuthoritiesForUserPrincipal.getAuthority());
            Optional.ofNullable(authorities)
                    .ifPresent(authoritiesList -> {
                        Set<Authority> currentAuthorities = userPrincipal.getAuthorities();
                        currentAuthorities.addAll(authoritiesList);
                        userPrincipalSaved.set(userPrincipalRepository.save(userPrincipal));
                    });
        }
        return userPrincipalSaved.get();
    }
}
