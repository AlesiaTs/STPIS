package com.example.alesiaproj.controller;

import com.example.alesiaproj.entity.CompanyInfo;
import com.example.alesiaproj.service.CompanyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/companyInfo")
public class CompanyInfoController {
    private final CompanyInfoService companyInfoService;

    @GetMapping("/{name}")
    public ResponseEntity<CompanyInfo> getByCompanyName(@PathVariable String name) {
        Optional<CompanyInfo> companyInfoOptional = companyInfoService.getByCompanyName(name);
        if (companyInfoOptional.isPresent()) {
            ResponseEntity.ok(companyInfoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CompanyInfo companyInfo) {
        companyInfoService.create(companyInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateByComanyName(@RequestBody CompanyInfo companyInfo) {
        companyInfoService.update(companyInfo);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/updateById")
    public ResponseEntity updateById(@RequestBody CompanyInfo companyInfo){
        companyInfoService.updateById(companyInfo);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CompanyInfo> getById(@PathVariable String id) {
        Optional<CompanyInfo> companyInfoOptional = companyInfoService.getById(id);
        if (companyInfoOptional.isPresent()) {
            return ResponseEntity.ok(companyInfoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(companyInfoService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyInfo>> getAll() {
        return ResponseEntity.ok(companyInfoService.getAll());
    }
    @GetMapping("/getByAdminEmail/{adminEmail}")
    public ResponseEntity<List<CompanyInfo>> getAllByAdminEmail(String email){
        return ResponseEntity.ok(companyInfoService.getAll().stream().filter(x->x.getAdminEmail().equals(email)).collect(Collectors.toList()));
    }
}
