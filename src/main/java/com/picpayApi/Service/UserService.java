package com.picpayApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picpayApi.Model.User;
import com.picpayApi.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {

        return userRepository.save(user);
    }
}
