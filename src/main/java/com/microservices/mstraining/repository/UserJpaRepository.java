package com.microservices.mstraining.repository;

import com.microservices.mstraining.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
