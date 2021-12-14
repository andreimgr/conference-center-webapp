package com.conferencecenterwebapp.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="webapp_user_accounts")
public class AppUser implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @NotBlank
    @Max(30)
    @Column(name="username")
    private String username;

    @NotBlank
    @Min(5)
    @Column(name="password")
    private String password;

    @Email
    @NotBlank
    @Column(name="email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "webapp_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppUserRole> appUserRole = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
