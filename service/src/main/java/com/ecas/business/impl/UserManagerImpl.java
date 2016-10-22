package com.ecas.business.impl;

import com.ecas.business.UserManager;
import com.ecas.domain.User;
import com.ecas.service.ActiveProfileEmailService;
import com.ecas.service.InviteEmailService;
import com.ecas.service.UserDataService;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Implement User management rule
 *
 * @author LENOVO
 */
@Service("userManager")
public class UserManagerImpl extends AbstractBusinessService implements UserManager, Serializable {
    private static final long serialVersionUID = 7025549672485652226L;
    @Autowired
    private InviteEmailService inviteEmailService;

    @Autowired
    private ActiveProfileEmailService profileEmailService;


    @Override
    public void invite(User user, User inviteBy) {
        try {
            getDataService().invite(user);
            inviteEmailService.sendInviteEmail((User) SerializationUtils.clone(user), inviteBy);
        } catch (Exception ex) {
            //TODO log
        }
    }

    @Override
    public void register(User user) {
        User userInfo = getDataService().save(user);
        profileEmailService.sendActivateEmail(userInfo);
    }

    private UserDataService getDataService() {
        return (UserDataService) getServiceRegistry().getService(User.class);
    }


}
