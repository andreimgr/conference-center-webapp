package com.conferencecenterwebapp.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UsernameAndPasswordAuthRequest {
    private String username;
    private String password;
}
