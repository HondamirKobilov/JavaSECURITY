package com.example.spring_security.Controller;

import com.example.spring_security.DTO.ApiResponse;
import com.example.spring_security.DTO.UserDto;
import com.example.spring_security.Entity.Users;
import com.example.spring_security.Repository.UserRepository;
import com.example.spring_security.Servis.UserServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userAPI")
public class UserController{
    @Autowired
    UserServis userServis;

//    @PreAuthorize(value = "hasRole('ADMIN')") 2-usul
    @PreAuthorize(value = "hasAuthority('ADDUSER')")
    @PostMapping("/postUser")
    HttpEntity<?> post(@RequestBody UserDto userDto){
        ApiResponse apiResponse = userServis.userPost(userDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPERUSER')")
    @PreAuthorize(value = "hasAuthority('GETUSER')")
    @GetMapping("/get")
    public HttpEntity<?> getUsers(){
        ApiResponse apiResponse = userServis.getUsers();
        return ResponseEntity.status( HttpStatus.OK).body(apiResponse.getXabar());
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPERUSER','USER')")
    @PreAuthorize(value = "hasAuthority('GETUSERBYID')")
    @GetMapping("/getby/{id}")
    public HttpEntity<?> getUsersId(@PathVariable Integer id){
        ApiResponse apiResponse = userServis.getUsers(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK: HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @PreAuthorize(value = "hasAuthority('DELETEUSER')")
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> deletXaridor(@PathVariable Integer id){
        ApiResponse apiResponse = userServis.Usersdelet(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }

}
