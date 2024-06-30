package org.yascode.security_service.service;

import org.yascode.security_service.controller.request.CreateAuthority;
import org.yascode.security_service.entity.Authority;

public interface AuthorityService {
    Authority createAuthority(CreateAuthority createAuthority);
}
