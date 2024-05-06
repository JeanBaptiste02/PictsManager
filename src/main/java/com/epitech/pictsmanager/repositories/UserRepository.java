package com.epitech.pictsmanager.repositories;

import org.springframework.stereotype.Repository;
import com.epitech.pictsmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserById(Long id);
    User findUserByEmail(String email);

    Optional<User> findByEmail(String email);

}
