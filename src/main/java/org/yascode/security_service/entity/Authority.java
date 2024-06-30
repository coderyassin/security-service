package org.yascode.security_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String authority;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    @JsonIgnore
    private Set<UserPrincipal> users = new HashSet<>();
}
