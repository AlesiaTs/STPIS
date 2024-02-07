package com.example.alesiaproj.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfo {
    private String id;
    private String name;
    private String email;
    private String position;
    private String role;

    public UserInfo(String name, String email, String position, String role) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.role = role;
    }
}
