package org.yascode.security_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.security_service.controller.api.AuthorityApi;
import org.yascode.security_service.controller.request.CreateAuthority;
import org.yascode.security_service.service.AuthorityService;

@RestController
@RequestMapping(value = "authority")
public class AuthorityController implements AuthorityApi {
    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    public ResponseEntity<?> createAuthority(CreateAuthority createAuthority) {
        return ResponseEntity.ok(this.authorityService.createAuthority(createAuthority));
    }
}
