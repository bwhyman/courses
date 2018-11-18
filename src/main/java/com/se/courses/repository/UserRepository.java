package com.se.courses.repository;

import com.se.courses.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 查询指定用户名密码的用户
     * @param number
     * @param password
     * @return
     */
    @Query("FROM User u WHERE u.number=:num AND u.password=:pwd")
    User find(@Param("num") String number, @Param("pwd") String password);
}
