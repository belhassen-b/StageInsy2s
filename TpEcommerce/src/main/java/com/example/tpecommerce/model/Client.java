package com.example.tpecommerce.model;


import com.example.tpecommerce.util.annotation.Password;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = -5846629789020830989L;

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "client_id", nullable = false)
    private Integer id;

    @NotBlank(message = "Firstname is mandatory")
    @Column(name = "name", nullable = false, length = 50)
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 60)
    @Column(name = "password", nullable = false)
    @JsonIgnore
    @Password
    private String password;


    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;


    @ManyToOne
    @JoinColumn(name = "role_name")
    private Role role;


    @OneToMany(mappedBy = "client")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { return username;    }


@Override
public boolean isAccountNonExpired() {
    return true;
}

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void prePersist() {
        uuid = java.util.UUID.randomUUID();
    }

}
