package com.ecas.web.admin.bean;

import com.ecas.domain.User;
import com.ecas.web.service.AuthenticationService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class LoginBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 6274956218977475134L;
    static Logger log = Logger.getLogger(LoginBean.class.getName());

    @ManagedProperty(value = "#{authenticationService}")
    private AuthenticationService authenticationService;
    @ManagedProperty(value = "#{languageBean}")
    private LanguageBean languageBean;

    private User user;

    @PostConstruct
    public void init() {
        setUser(new User());
    }

    public String login() {
        try {
            if (authenticationService.login(user.getEmail(), user.getPassword())) {
                reloadWorkerContext();

                if (getCurrentUser().isDelete()) {
                    return "user/deleted?faces-redirect=true";
                } else {
                    if (getCurrentUser().isEnabled()) {
                        return "/views/organization/dashboard?faces-redirect=true";
                    } else {
                        return "user/activate?faces-redirect=true";
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Login failed: ", e);
            addWarnMessage("com.cas.sign.in.invalid.email_or_pass");
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the authenticationService
     */
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    /**
     * @param authenticationService the authenticationService to set
     */
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public LanguageBean getLanguageBean() {
        return languageBean;
    }

    public void setLanguageBean(LanguageBean languageBean) {
        this.languageBean = languageBean;
    }

}
