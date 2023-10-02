package com.example.tpecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "authority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 506784878897L;

    @NotBlank
    @Size(min = 5, max = 20)
    @Id
    @Column(name = "name", length = 20)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
