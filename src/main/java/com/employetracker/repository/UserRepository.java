package com.employetracker.repository;

import com.employetracker.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(int userId);

    User findByEmail(String email);

    User findByResetToken(String token);

    User findByMobNumber(Double number);


}
