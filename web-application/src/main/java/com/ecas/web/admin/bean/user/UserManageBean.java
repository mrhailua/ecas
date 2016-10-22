package com.ecas.web.admin.bean.user;

import com.ecas.business.UserManager;
import com.ecas.domain.User;
import com.ecas.service.UserDataService;
import com.ecas.web.admin.bean.BaseBean;
import com.ecas.web.admin.bean.LanguageBean;
import com.ecas.web.admin.model.DomainSelectedModel;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserManageBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1752434090518324699L;
    static Logger log = Logger.getLogger(UserManageBean.class.getName());
    private DomainSelectedModel<User> dataModel;
    private User selectedUser;
    private User replaceBy;
    private User editUser;
    private List<User> users;
    private List<User> replaceByUsers;

    @ManagedProperty(value = "#{encoder}")
    private BCryptPasswordEncoder encoder;
    @ManagedProperty(value = "#{userManager}")
    private UserManager userManager;
    @ManagedProperty(value = "#{languageBean}")
    private LanguageBean languageBean;

    @PostConstruct
    public void init() {
    }

    public void addUser(ActionEvent actionEvent) {
        setEditUser(new User());
    }


    public void onRowEdit(RowEditEvent event) {
        try {
            User user = (User) event.getObject();
            getServiceRegistry().getService(User.class).save(user);
        } catch (Exception e) {
            log.error("Handle User error", e);
            addWarnMessage("leave.look.org.init.edit.error");
        }
    }

    public void doInviteUser() {
        try {
            this.userManager.invite(editUser, getCurrentUser());
            editUser.setPassword(encoder.encode(editUser.getPassword()));
            hideDialog("dlg");
            addInfoMessage("com.org.invite.sucessfully", editUser.getEmail());
        } catch (Exception e) {
            log.error("Save User error", e);
            addWarnMessage("iwork.create.user.invalid.msg", editUser.getName());
        }
    }

    private UserDataService getUserDataService() {
        return (UserDataService) getServiceRegistry().getService(User.class);
    }

    private UserDataService getDataService() {
        return (UserDataService) getServiceRegistry().getService(User.class);
    }

    public DomainSelectedModel<User> getDataModel() {
        return dataModel;
    }

    public void setDataModel(DomainSelectedModel<User> dataModel) {
        this.dataModel = dataModel;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getEditUser() {
        return editUser;
    }

    public void setEditUser(User editUser) {
        this.editUser = editUser;
    }

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User getReplaceBy() {
        return replaceBy;
    }

    public void setReplaceBy(User replaceBy) {
        this.replaceBy = replaceBy;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getReplaceByUsers() {
        return replaceByUsers;
    }

    public void setReplaceByUsers(List<User> replaceByUsers) {
        this.replaceByUsers = replaceByUsers;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
