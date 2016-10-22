package com.ecas.business;

import com.ecas.domain.User;

/**
 * Implement user manage business rule
 *
 * @author LENOVO
 */
public interface UserManager {

    void invite(User user, User inviteBy);

    void register(User user);
}
