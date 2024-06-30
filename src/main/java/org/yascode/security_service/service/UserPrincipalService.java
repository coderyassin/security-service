package org.yascode.security_service.service;

import org.yascode.security_service.controller.request.AddAuthoritiesForUserPrincipal;
import org.yascode.security_service.controller.request.UserWithAuthorities;
import org.yascode.security_service.entity.UserPrincipal;

public interface UserPrincipalService {
    UserPrincipal createUserPrincipal(UserWithAuthorities userWithAuthorities);

    UserPrincipal addAuthoritiesForUserPrincipal(AddAuthoritiesForUserPrincipal addAuthoritiesForUserPrincipal);
}
