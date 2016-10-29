package com.ecas.domain;

import com.ecas.annotation.ApplicationStatus;
import com.ecas.annotation.UserStatus;
import com.ecas.base.BaseDomain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ecas_application")
public class Application extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @OneToMany(mappedBy = "application")
    private List<API> apis;

    @OneToMany(mappedBy = "application")
    private List<Role> roles;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<API> getApis() {
        return apis;
    }

    public void setApis(List<API> apis) {
        this.apis = apis;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void enable() {
        this.status = ApplicationStatus.ENABLE;
    }

    public void disable() {
        this.status = ApplicationStatus.DISABLE;
    }

    public boolean isEnable() {
        return this.status != null && this.status == ApplicationStatus.ENABLE;
    }

}