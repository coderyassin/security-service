package org.yascode.security_service.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.security_service.controller.request.CreateAuthority;
import org.yascode.security_service.entity.Authority;
import org.yascode.security_service.repository.AuthorityRepository;
import org.yascode.security_service.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority createAuthority(CreateAuthority createAuthority) {
        Authority authority = Authority.builder()
                                       .authority(createAuthority.getAuthority())
                                       .build();
        return this.authorityRepository.save(authority);
    }
}
