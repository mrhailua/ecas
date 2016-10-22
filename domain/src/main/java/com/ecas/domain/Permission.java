package com.ecas.domain;

import com.ecas.base.BaseDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ecas_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "permission_cache")
public class Permission extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 8248943666497556860L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active")
    private boolean active;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "application_id")
    private Application application;

    @Column(name = "approve_user_id")
    private User approver;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
