package com.conferencecenterwebapp.controller;

import com.conferencecenterwebapp.model.AppUser;
import com.conferencecenterwebapp.model.AppUserRole;
import com.conferencecenterwebapp.repository.AppUserRepository;
import com.conferencecenterwebapp.service.AppUserService;
import com.conferencecenterwebapp.service.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserService appUserService,
                             AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody AppUser appUser) {
        appUserService.signupAppUser(appUser);
        return ResponseEntity.ok(new MessageResponse("Signup successfully"));
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        AppUser userFound = appUserRepository.findAppUserByUsername(username);

        String userFoundUsername = userFound.getUsername();
        String userFoundEmail = userFound.getEmail();
        Set<AppUserRole> userFoundRole = userFound.getAppUserRole();


        Map<Object, Object> model = new HashMap<>();
        model.put("username", userFoundUsername);
        model.put("email", userFoundEmail);
        model.put("role", userFoundRole);

        return ResponseEntity.ok().body(model);
    }
}
