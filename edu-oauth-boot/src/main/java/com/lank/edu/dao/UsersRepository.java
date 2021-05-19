package com.lank.edu.dao;

import com.lank.edu.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LanceLan
 * @since 2021/5/19 11:16
 */
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
}
