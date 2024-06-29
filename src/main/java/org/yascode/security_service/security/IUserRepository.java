package org.yascode.security_service.security;

public interface IUserRepository {
    User findByUsername(String username);
}
