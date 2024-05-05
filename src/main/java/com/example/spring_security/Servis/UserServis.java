package com.example.spring_security.Servis;

import com.example.spring_security.DTO.ApiResponse;
import com.example.spring_security.DTO.UserDto;
import com.example.spring_security.Entity.Users;
import com.example.spring_security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServis {
    @Autowired
    UserRepository userRepository;

    public ApiResponse userPost(UserDto userDto) {
        boolean b = userRepository.existsByLogin(userDto.getLogin());
        if(b) return new ApiResponse("Bunday loginli users mavjud!!!",false);
        Users user = new Users();
        user.setIsmi(userDto.getIsmi());
        user.setFamilyasi(userDto.getFamilyasi());
        user.setLogin(userDto.getLogin());
        user.setParol(userDto.getParol());
        userRepository.save(user);
        return new ApiResponse("Malumot saqlandi!!!",true);
    }

    public ApiResponse getUsers() {
        return new ApiResponse(userRepository.findAll().toString(),true);
    }

    public ApiResponse getUsers(Integer id) {
        Optional<Users> byId = userRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse (id+"-idili malumot mavjud emas!!!",false);
        }
        return new ApiResponse(userRepository.findById(id).toString(),true);
    }


    public ApiResponse Usersdelet(Integer id) {
        Optional<Users> byId = userRepository.findById(id);
        if(!byId.isPresent()) return new ApiResponse(id+"-idli malumot topilmadi!!!",false);
        Users users = byId.get();
        userRepository.delete(users);
        return new ApiResponse(userRepository.toString(),true);
    }
}
