package com.example.alesiaproj.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyInfo {
    private String id;
    public String name;
    public String sphere;
    public String adminEmail;
    public List<String> clientEmails;

    public CompanyInfo(String name, String sphere, String adminEmail, List<String> clientEmails) {
        this.name = name;
        this.sphere = sphere;
        this.adminEmail = adminEmail;
        this.clientEmails = clientEmails;
    }
}
