package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_ID")
    private Integer accountID;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private String role;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int isDeleted = 0;

    @Transient
    private String tokens;

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Transient
    @Override
    public String getUsername() {
        return this.name;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Constructors, getters, setters, etc.
}
