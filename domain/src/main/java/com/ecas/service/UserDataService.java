package com.ecas.service;

import com.ecas.domain.User;
import com.ecas.exception.UserExistedException;

public interface UserDataService extends GenericDataService<User> {

    User create(User obj) throws UserExistedException;

    User update(User obj);

    User invite(User obj) throws UserExistedException;

    User getUserByEmail(String email);

    User regenPassword(User obj) throws UserExistedException;

}
