package com.example.alesiaproj.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    private String id;
    private String title;
    private String description;
    private String worker;
    private int workHourCount;
    private String status;
    private String dateToComplete;
}
