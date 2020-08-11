package com.mindtree.authorization.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindtree.authorization.entity.CustomUserDetails;
import com.mindtree.authorization.entity.User;
import com.mindtree.authorization.respository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepo.findById(userId);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
        
        System.out.println(optionalUser.get().getUserId()+ " "+ optionalUser.get().getPasswordBycrptor() );
        
        UserDetails userDetails = new CustomUserDetails(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;

    }
}