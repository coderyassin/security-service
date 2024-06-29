package org.yascode.security_service.security;

import org.springframework.stereotype.Repository;
import org.yascode.security_service.config.AppConfig;

import java.util.List;

@Repository
public class RoleRepository implements IRoleRepository {
    private final AppConfig appConfig;

    public RoleRepository(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public List<Role> findByUser(Long userId) {
        return appConfig.getRoles()
                .stream()
                .filter(role -> role.getUserId().equals(userId))
                .toList();
    }
}
