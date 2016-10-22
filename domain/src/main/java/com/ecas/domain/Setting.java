package com.ecas.domain;

import com.ecas.base.BaseDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the worker database table.
 */
@Entity
@DynamicUpdate
@Table(name = "ecas_setting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "setting")
public class Setting extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "timezone", length = 10)
    private String timezone;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "web_domain")
    private String webdomain;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getWebdomain() {
        return webdomain;
    }

    public void setWebdomain(String webdomain) {
        this.webdomain = webdomain;
    }
}