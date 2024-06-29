package org.yascode.security_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.security_service.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
