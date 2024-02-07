package com.example.alesiaproj.dto;

import com.example.alesiaproj.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfoDto {
    private UserInfo userInfo;
    private String companyName;
}
