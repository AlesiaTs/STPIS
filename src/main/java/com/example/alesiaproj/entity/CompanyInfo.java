package com.example.alesiaproj.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyInfo {
    private String id;
    private String name;
    private String sphere;
    private String adminEmail;
    private List<String> clientEmails;

    public CompanyInfo(String name, String sphere, String adminEmail, List<String> clientEmails) {
        this.name = name;
        this.sphere = sphere;
        this.adminEmail = adminEmail;
        this.clientEmails = clientEmails;
    }
}
