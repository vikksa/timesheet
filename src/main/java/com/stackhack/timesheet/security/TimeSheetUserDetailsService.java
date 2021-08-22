package com.stackhack.timesheet.security;

import com.stackhack.timesheet.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class TimeSheetUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(TimeSheetUserDetailsService.class);

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsernameIgnoreCase(username)
                .map(TimeSheetUserDetails::new)
                .orElseThrow(() -> {
                    log.debug("Unknown username: {}", username);
                    return new UsernameNotFoundException(username);
                });
    }

}
