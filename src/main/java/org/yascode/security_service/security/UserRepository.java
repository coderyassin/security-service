package org.yascode.security_service.security;

import org.springframework.stereotype.Repository;
import org.yascode.security_service.config.AppConfig;

@Repository
public class UserRepository implements IUserRepository {
    private final AppConfig appConfig;

    public UserRepository(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public User findByUsername(String username) {
        return appConfig.getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
