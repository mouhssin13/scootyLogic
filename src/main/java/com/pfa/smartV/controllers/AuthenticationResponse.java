package com.pfa.smartV.controllers;

import com.pfa.smartV.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Long id;
    private String fullName;
    private String email;
    private String CIN;
    private String tel;
    private Role role;
    private String token;
}
