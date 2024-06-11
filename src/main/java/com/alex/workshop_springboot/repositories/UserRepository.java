package com.alex.workshop_springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.workshop_springboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
