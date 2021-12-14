package com.conferencecenterwebapp.service;

import com.conferencecenterwebapp.model.AppRole;
import com.conferencecenterwebapp.model.AppUser;
import com.conferencecenterwebapp.model.AppUserRole;
import com.conferencecenterwebapp.repository.AppUserRepository;
import com.conferencecenterwebapp.repository.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserService(PasswordEncoder passwordEncoder,
                          AppUserRepository appUserRepository,
                          AppUserRoleRepository appUserRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
    }



    public void signupAppUser(AppUser appUser) {
        AppUser newAppUser = new AppUser();
        newAppUser.setUsername(appUser.getUsername());
        newAppUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        newAppUser.setEmail(appUser.getEmail());

        // setting-up user role
        Set<AppUserRole> newAppUserRoles = new HashSet<>();
        AppUserRole newAppUserRole = appUserRoleRepository.findAppUserRoleByName(AppRole.USER);
        newAppUserRoles.add(newAppUserRole);
        newAppUser.setAppUserRole(newAppUserRoles);

        appUserRepository.save(newAppUser);
    }


}
