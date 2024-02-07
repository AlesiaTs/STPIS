package com.example.alesiaproj.service;

import com.example.alesiaproj.entity.CompanyInfo;
import com.example.alesiaproj.repository.FirebaseCompanyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final FirebaseCompanyInfoRepository companyInfoRepository;

    public Optional<CompanyInfo> getByCompanyName(String name) {
        try {
            return companyInfoRepository.getByName(name);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void updateById(CompanyInfo companyInfo) {
        try {
            companyInfoRepository.updateById(companyInfo);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void create(CompanyInfo companyInfo) {
        try {
            companyInfoRepository.create(companyInfo);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void update(CompanyInfo companyInfo) {
        try {
            companyInfoRepository.update(companyInfo);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public Optional<CompanyInfo> getById(String id) {
        try {
            return companyInfoRepository.getById(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

    public String deleteById(String id) {
        return companyInfoRepository.deleteById(id);
    }

    public List<CompanyInfo> getAll() {
        try {
            return companyInfoRepository.getAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }
}
