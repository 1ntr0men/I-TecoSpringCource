package ru.iteco.itecospringcource.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_auth", schema = "ad")
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_auth_roles", schema = "public", joinColumns = @JoinColumn(name = "user_auth_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles);
    }
}
