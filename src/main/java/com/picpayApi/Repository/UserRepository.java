package com.picpayApi.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.picpayApi.Model.User;

public interface UserRepository extends JpaRepository<User,UUID> {
    
}
