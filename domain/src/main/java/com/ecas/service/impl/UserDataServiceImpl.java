package com.ecas.service.impl;

import com.ecas.annotation.UserStatus;
import com.ecas.domain.User;
import com.ecas.exception.UserExistedException;
import com.ecas.service.GenerateCodeUtil;
import com.ecas.service.UserDataService;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("userDetailsService")
@Transactional
public class UserDataServiceImpl extends GenericDataServiceImpl<User> implements UserDataService,
        UserDetailsService, Serializable {
    private static final int ACTIVATION_CODE_LENGTH = 6;
    private static final int PASSWORD_LENGTH = 10;
    private static final long serialVersionUID = -2784021342134127018L;

    @Override
    public User create(User obj) throws UserExistedException {
        if (getUserWithEmail(obj.getEmail()) != null) {
            throw new UserExistedException();
        }
        obj.setActivationCode(GenerateCodeUtil.getCode(ACTIVATION_CODE_LENGTH));
        obj.setStatus(UserStatus.InActive);
        User userInfo = super.save(obj);
        return userInfo;
    }

    @Override
    public User update(User obj) {
        return super.save(obj);
    }

    @Override
    public User invite(User inviteUser) throws UserExistedException {
        if (getUserWithEmail(inviteUser.getEmail()) != null) {
            throw new UserExistedException();
        }
        inviteUser.setPassword(GenerateCodeUtil.getCode(PASSWORD_LENGTH));
        inviteUser.setActivationCode(GenerateCodeUtil.getCode(ACTIVATION_CODE_LENGTH));
        inviteUser.setStatus(UserStatus.InActive);
        return this.save(inviteUser);
    }

    private User getUserWithEmail(String email) {
        Criteria criteria = this.getCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User regenPassword(User obj) throws UserExistedException {
        obj.setPassword(GenerateCodeUtil.getCode(PASSWORD_LENGTH));
        return obj;
    }

    @Override
    public User getUserByEmail(String email) {
        Criteria criteria = this.getCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (User) criteria.uniqueResult();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Criteria criteria = getCriteria();
        User worker = (User) criteria.add(Restrictions.eq("email", username))
                .add(Restrictions.eq("userEnable", true)).setFetchMode("organization", FetchMode.JOIN)
                .setFetchMode("department", FetchMode.JOIN).setFetchMode("department.location", FetchMode.JOIN)
                .setFetchMode("avatar", FetchMode.JOIN).uniqueResult();
        if (worker == null) {
            throw new UsernameNotFoundException("There is no user with email:" + username);
        }
        return worker;
    }
}
