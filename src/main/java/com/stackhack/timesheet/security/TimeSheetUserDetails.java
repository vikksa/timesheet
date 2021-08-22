package com.stackhack.timesheet.security;

import com.stackhack.timesheet.models.TimeSheetUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


public class TimeSheetUserDetails implements UserDetails {


//    public static List<GrantedAuthority> buildAuthorities(DocutoolsUser user) {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        if(user.getSettings().isAdmin()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
//        }
//        if(user.getSettings().isProjectCreator()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("project_creator"));
//        }
//        if(user.isOrganisationOwner()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("owner"));
//        }
//        if(Validator.isSustainUser(user.getUsername())){
//            grantedAuthorities.add(new SimpleGrantedAuthority("sustain_user"));
//        }
//        return grantedAuthorities;
//    }

    private TimeSheetUser user;
    private List<GrantedAuthority> grantedAuthorities;
    private boolean passwordNotExpired = true;


    public TimeSheetUserDetails(TimeSheetUser user) {
        Assert.notNull(user);
        this.user = user;
        // Set granted authorities
//        this.grantedAuthorities = buildAuthorities(user);
    }

    /**
     * Gets the database entity for this user.
     *
     * @return database entity
     */
    public TimeSheetUser getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordNotExpired;
    }

    public void setPasswordNotExpired(boolean passwordNotExpired) {
        this.passwordNotExpired = passwordNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return user.getArchived();
    }

    /**
     * Gets the unique ID of this user.
     *
     * @return {@link UUID}
     */
    public UUID getId() {
        return user.getId();
    }
}
