package com.epitech.pictsmanager.repositories;

import org.springframework.stereotype.Repository;
import com.epitech.pictsmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
