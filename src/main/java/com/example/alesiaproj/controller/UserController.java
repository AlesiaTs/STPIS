package com.example.alesiaproj.controller;

import com.example.alesiaproj.dto.UserInfoDto;
import com.example.alesiaproj.entity.UserInfo;
import com.example.alesiaproj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getByUserId(@PathVariable String id) {
        Optional<UserInfo> userInfoOptional = userService.getByUserId(id);
        if (userInfoOptional.isPresent()) {
            return ResponseEntity.ok(userInfoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createClient")
    public ResponseEntity createClient(@RequestBody UserInfoDto userInfoDto) {
        if (userInfoDto.userInfo.email != null && userInfoDto.userInfo.name != null && userInfoDto.userInfo.role!= null && userInfoDto.userInfo.position!= null)
        {userService.registerUserAsClient(userInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();}
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST message 'can not created' ");
    }

    @PostMapping("/createAdmin")
    public ResponseEntity createAdmin(@RequestBody UserInfo userInfo) {
        userService.registerUserAsAmin(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity updateById(@RequestBody UserInfo userInfo){
        if (userInfo.email != null && userInfo.name != null && userInfo.role!= null && userInfo.position!= null) {
            userService.updateUserById(userInfo);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST message 'can not created' ");

    }
    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity deleteByEmail(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<UserInfo> getByEmail(@PathVariable String email) {
        Optional<UserInfo> userInfo = userService.getByEmail(email);
        if (userInfo.isPresent()) {
            return ResponseEntity.ok(userInfo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserInfo>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}
