package com.ecas.web.admin.bean;

import com.ecas.domain.User;
import com.ecas.service.ResetPasswordEmailService;
import com.ecas.service.UserDataService;
import com.ecas.web.service.AuthenticationService;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class ProfileBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 6274956218977475134L;
    static Logger log = Logger.getLogger(ProfileBean.class.getName());
    private User user;
    private String repeatPassword;
    private String currentPassword;
    private String newPassword;
    private String activationCode;
    private String emailToResetPass;

    @ManagedProperty(value = "#{encoder}")
    private BCryptPasswordEncoder encoder;
    @ManagedProperty(value = "#{resetEmailService}")
    private ResetPasswordEmailService resetEmailService;
    @ManagedProperty(value = "#{authenticationService}")
    private AuthenticationService authenticationService;

    @PostConstruct
    public void init() {
        if (getCurrentUser() != null) {
            setUser(getServiceRegistry().getService(User.class).get(getCurrentUser().getId()));
        } else {
            setUser(new User());
        }

    }

    public void changePass() {
        try {
            authenticationService.login(user.getEmail(), currentPassword);
            setUser(getServiceRegistry().getService(User.class).get(getCurrentUser().getId()));
            user.setPassword(encoder.encode(newPassword));
            getUserDataService().save(user);
            addInfoMessage("com.cas.change.pass.sucessfully");
            hideDialog("changePassDialog");
        } catch (BadCredentialsException ex) {
            addWarnMessage("com.cas.sign.in.invalid.pass");
        } catch (Exception e) {
            log.error("Save profile fail", e);
            addWarnMessage("iwork.sign.up.create.error", user.getEmail());
        }
    }

    private UserDataService getUserDataService() {
        return (UserDataService) getServiceRegistry().getService(User.class);
    }

    public String activateUserProfile() {
        try {
            if (StringUtils.equals(user.getActivationCode(), activationCode)) {
                user.activate();
                getServiceRegistry().getService(User.class).save(user);
                addInfoMessage("iwork.activate.successfull");
                return "/views/organization/dashboard?faces-redirect=true";
            } else {
                addWarnMessage("iwork.activate.error");
            }
        } catch (Exception e) {
            log.error("Save profile fail", e);
            addWarnMessage("iwork.sign.up.create.error", user.getEmail());
        }
        return null;
    }

    public String resendActivateCode() {
        try {
            getUserDataService().save(user);
            addInfoMessage("iwork.resend.activate.code.sucessfully", user.getEmail());
        } catch (Exception e) {
            log.error("Save profile fail", e);
            addWarnMessage("iwork.sign.up.create.error", user.getEmail());
        }
        return null;
    }

    public void resetPassword() {
        try {
            UserDataService workerService = getUserDataService();
            User workerToReset = workerService.getUserByEmail(emailToResetPass);
            workerToReset = workerService.regenPassword(workerToReset);
            if (!workerToReset.isEnabled()) {
                addInfoMessage("iwork.profile.assign.account.block");
            } else {
                resetEmailService.sendResetEmail(SerializationUtils.clone(workerToReset));
                workerToReset.setPassword(encoder.encode(workerToReset.getPassword()));
                getUserDataService().save(workerToReset);
                addInfoMessage("iwork.profile.assign.reset.sucessfull", emailToResetPass);
            }
        } catch (Exception e) {
            log.error("Reset password", e);
            addWarnMessage("iwork.profile.assign.reset.error", emailToResetPass);
        }
    }

    public void saverUserProfile() {
        try {
            getServiceRegistry().getService(User.class).save(user);
            addInfoMessage("iwork.sign.up.updsate.sucessfully", user.getEmail());
        } catch (Exception e) {
            log.error("Save profile fail", e);
            addWarnMessage("iwork.sign.up.create.error", user.getEmail());
        }
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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
     * @return the emailToResetPass
     */
    public String getEmailToResetPass() {
        return emailToResetPass;
    }

    /**
     * @param emailToResetPass the emailToResetPass to set
     */
    public void setEmailToResetPass(String emailToResetPass) {
        this.emailToResetPass = emailToResetPass;
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
}
