package org.yascode.security_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.yascode.security_service.controller.request.CreateAuthority;

public interface AuthorityApi {
    @PostMapping(value = "/createAuthority")
    ResponseEntity<?> createAuthority(@RequestBody CreateAuthority createAuthority);
}
