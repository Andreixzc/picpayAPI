package com.picpayApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.picpayApi.Model.User;
import com.picpayApi.Service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid  User user){
        System.out.println("PRITNANDO DADOS:: @@@@@@@@@@@@@@@");
        System.out.println(user.toString());
        return ResponseEntity.ok(userService.create(user));
    }
}
