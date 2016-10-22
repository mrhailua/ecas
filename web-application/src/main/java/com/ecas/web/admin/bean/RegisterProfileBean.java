package com.ecas.web.admin.bean;

import com.ecas.domain.User;
import com.ecas.service.ResetPasswordEmailService;
import com.ecas.service.UserDataService;
import com.ecas.web.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class RegisterProfileBean extends BaseBean implements Serializable {
    private static final int ACCOUNT_LIMIT = 10;
    private static final long serialVersionUID = 6274956218977475134L;
    static Logger log = Logger.getLogger(RegisterProfileBean.class.getName());

    private User user;
    private String repeatPassword;
    private String currentPassword;
    private String newPassword;
    private boolean importConfigure;
    private String currentStep = "";

    @ManagedProperty(value = "#{encoder}")
    private BCryptPasswordEncoder encoder;
    @ManagedProperty(value = "#{resetEmailService}")
    private ResetPasswordEmailService resetEmailService;
    @ManagedProperty(value = "#{authenticationService}")
    private AuthenticationService authenticationService;
    @ManagedProperty(value = "#{languageBean}")
    private LanguageBean languageBean;

    @PostConstruct
    public void init() {
        if (getCurrentUser() != null) {
            setUser(getServiceRegistry().getService(User.class).get(getCurrentUser().getId()));
        } else {
            setUser(new User());
        }

        currentStep = "personal";

    }

    public String onFlowProcess(FlowEvent event) {
        currentStep = event.getNewStep();
        update("buttonArea");
        return event.getNewStep();
    }

    @Transactional
    public String registerUserProfile() {
        try {
            user.setPassword(encoder.encode(repeatPassword));
            getUserDataService().save(user);
            authenticationService.login(user.getEmail(), repeatPassword);
            addInfoMessage("ruacms.sign.up.create.sucessfully", user.getEmail());
            return "/views/user/activate?faces-redirect=true";
        } catch (Exception e) {
            log.error("Save profile fail", e);
            addWarnMessage("iwork.sign.up.create.error", user.getEmail());
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * @return the encoder
     */
    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    /**
     * @param encoder the encoder to set
     */
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * @return the resetEmailService
     */
    public ResetPasswordEmailService getResetEmailService() {
        return resetEmailService;
    }

    /**
     * @param resetEmailService the resetEmailService to set
     */
    public void setResetEmailService(ResetPasswordEmailService resetEmailService) {
        this.resetEmailService = resetEmailService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean isImportConfigure() {
        return importConfigure;
    }

    public void setImportConfigure(boolean importConfigure) {
        this.importConfigure = importConfigure;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    private UserDataService getUserDataService() {
        return (UserDataService) getServiceRegistry().getService(User.class);
    }

    private UserDataService getDataService() {
        return (UserDataService) getServiceRegistry().getService(User.class);
    }
}
