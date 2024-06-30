package org.yascode.security_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.security_service.controller.api.UserPrincipalApi;
import org.yascode.security_service.controller.request.AddAuthoritiesForUserPrincipal;
import org.yascode.security_service.controller.request.UserWithAuthorities;
import org.yascode.security_service.service.UserPrincipalService;

@RestController
@RequestMapping(value = "/userPrincipal")
public class UserPrincipalController implements UserPrincipalApi {
    private final UserPrincipalService userPrincipalService;

    public UserPrincipalController(UserPrincipalService userPrincipalService) {
        this.userPrincipalService = userPrincipalService;
    }

    @Override
    public ResponseEntity<?> createUserPrincipal(UserWithAuthorities userWithAuthorities) {
        return ResponseEntity.ok(userPrincipalService.createUserPrincipal(userWithAuthorities));
    }

    @Override
    public ResponseEntity<?> addAuthoritiesForUserPrincipal(AddAuthoritiesForUserPrincipal addAuthoritiesForUserPrincipal) {
        return ResponseEntity.ok(userPrincipalService.addAuthoritiesForUserPrincipal(addAuthoritiesForUserPrincipal));
    }
}
