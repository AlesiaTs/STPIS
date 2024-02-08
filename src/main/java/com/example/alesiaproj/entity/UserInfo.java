package com.example.alesiaproj.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfo {
    private String id;
    public String name;
    public String email;
    public String position;
    public String role;

    public UserInfo(String name, String email, String position, String role) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.role = role;
    }
}
