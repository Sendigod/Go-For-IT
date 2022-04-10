package com.lhs.ms.goforit.repository;

import com.lhs.ms.goforit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 16:28
 * @Description :
 */
@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);


}
