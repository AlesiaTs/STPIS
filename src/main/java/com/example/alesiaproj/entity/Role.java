package com.example.alesiaproj.entity;

public enum Role {
    CLIENT("CLIENT"),ADMIN("ADMIN");
    String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
