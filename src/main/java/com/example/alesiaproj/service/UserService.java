package com.example.alesiaproj.service;

import com.example.alesiaproj.dto.UserInfoDto;
import com.example.alesiaproj.entity.CompanyInfo;
import com.example.alesiaproj.entity.Role;
import com.example.alesiaproj.entity.UserInfo;
import com.example.alesiaproj.exception.InvalidDataForRegistrationError;
import com.example.alesiaproj.repository.FirebaseUserInfoRepository;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query;
import com.sun.net.httpserver.HttpExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final FirebaseUserInfoRepository firebaseUserInfoRepository;

    public void registerUserAsClient(UserInfoDto userInfoDto) {
        try {
            validateEmailIsCompanyExist(userInfoDto.getUserInfo().getEmail(), userInfoDto.getCompanyName());
        }

        catch (ExecutionException | InterruptedException e) {
            throw new InvalidDataForRegistrationError();
        }

        userInfoDto.getUserInfo().setRole(Role.CLIENT.name());
        try {
            firebaseUserInfoRepository.create(userInfoDto.getUserInfo());
        } catch (ExecutionException | InterruptedException e) {
           throw new RuntimeException();
        }

    }


    public void registerUserAsAmin(UserInfo userInfo) {
        try {
            firebaseUserInfoRepository.create(userInfo);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public Optional<UserInfo> getByUserId(String id) {
        try {
            return firebaseUserInfoRepository.getById(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

    public String deleteById(String documentId) {
        return firebaseUserInfoRepository.deleteById(documentId);
    }

    public String deleteByEmail(String email) {
        try {
            return firebaseUserInfoRepository.deleteByEmail(email);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

    public Optional<UserInfo> getByEmail(String email) {
        try {
            return firebaseUserInfoRepository.getByEmail(email);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

    public void updateUserById(UserInfo userInfo) {
        try {
            firebaseUserInfoRepository.update(userInfo);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public List<UserInfo> getAll() {
        try {
            return firebaseUserInfoRepository.getAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

    private boolean validateEmailIsCompanyExist(String email, String companyName) throws ExecutionException, InterruptedException {
        Optional<CompanyInfo> companyInfoOptional = firebaseUserInfoRepository.getByCompanyName(companyName);
        if (companyInfoOptional.isPresent()) {
            CompanyInfo companyInfo = companyInfoOptional.get();
            return companyInfo.getClientEmails().contains(email);
        }
        return false;
    }



}
