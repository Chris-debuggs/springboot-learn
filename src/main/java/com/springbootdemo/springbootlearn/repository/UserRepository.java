package com.springbootdemo.springbootlearn.repository;

import com.springbootdemo.springbootlearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
