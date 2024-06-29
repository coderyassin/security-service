package org.yascode.security_service.security;

import java.util.List;

public interface IRoleRepository {
    List<Role> findByUser(Long userId);
}
