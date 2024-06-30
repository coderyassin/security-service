package org.yascode.security_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.yascode.security_service.controller.request.AddAuthoritiesForUserPrincipal;
import org.yascode.security_service.controller.request.UserWithAuthorities;

public interface UserPrincipalApi {
    @PostMapping(value = "/createUserPrincipal")
    ResponseEntity<?> createUserPrincipal(@RequestBody UserWithAuthorities userWithAuthorities);

    @PostMapping(value = "/addAuthoritiesForUserPrincipal")
    ResponseEntity<?> addAuthoritiesForUserPrincipal(@RequestBody AddAuthoritiesForUserPrincipal addAuthoritiesForUserPrincipal);

}
