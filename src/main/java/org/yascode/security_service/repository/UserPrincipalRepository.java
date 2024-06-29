package org.yascode.security_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.security_service.entity.UserPrincipal;

public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Long> {
    UserPrincipal findByUsername(String username);
}
