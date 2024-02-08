package com.example.alesiaproj.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    private String id;
    public String title;
    public String description;
    public String worker;
    public int workHourCount;
    public String status;
    public String dateToComplete;


}
