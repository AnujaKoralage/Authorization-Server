package com.rent.a.car.auth.authserver.service;

import com.rent.a.car.auth.authserver.Repository.UserDetailRepository;
import com.rent.a.car.auth.authserver.model.AuthUserDetail;
import com.rent.a.car.auth.authserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userDetailRepository.findByUsername(name);
        user.orElseThrow(() ->new UsernameNotFoundException("User name or password not found"));

        UserDetails userDetails = new AuthUserDetail(user.get());

        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
