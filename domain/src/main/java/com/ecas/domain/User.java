package com.ecas.domain;

import com.ecas.annotation.UserStatus;
import com.ecas.base.AbstractDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "ecas_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ecas_user")
public class User extends AbstractDomain implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id = 0;

    @Column(name = "email")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "password")
    private String password;

    @Column(name = "activation_code", length = 45)
    private String activationCode;

    @Column(name = "expirable")
    private boolean expirable;

    @Column(name = "system_admin")
    private boolean systemAdmin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<Role>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != null && status != UserStatus.Expire;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != null && status != UserStatus.Lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status == null || status != UserStatus.Expire;
    }

    @Override
    public boolean isEnabled() {
        return status != null && status == UserStatus.Active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isDelete() {
        return status == null || status == UserStatus.Delete;
    }

    public void activate() {
        status = UserStatus.Active;
    }

    public boolean isExpirable() {
        return expirable;
    }

    public void setExpirable(boolean expirable) {
        this.expirable = expirable;
    }

    public boolean isSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(boolean systemAdmin) {
        this.systemAdmin = systemAdmin;
    }
}