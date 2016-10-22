package com.ecas.domain;

import com.ecas.base.BaseDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ecas_api")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ecas_api")
public class API extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 8248943666497556860L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "active")
    private boolean active;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="application_id")
    private Application application;

    @OneToMany(mappedBy="api")
    private List<APIKey> keys;

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

    public List<APIKey> getKeys() {
        return keys;
    }

    public void setKeys(List<APIKey> keys) {
        this.keys = keys;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
