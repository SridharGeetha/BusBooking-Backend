package com.online.bus.booking.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.bus.booking.Entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long>{
    Optional<MyUser> findByEmail(String email);
}
