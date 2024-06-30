package org.yascode.security_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yascode.security_service.entity.Authority;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("SELECT a FROM Authority a WHERE a.authority IN :authorities")
    List<Authority> findByAuthorities(@Param("authorities") List<String> authorities);

}
