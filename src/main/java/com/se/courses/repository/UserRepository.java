package com.se.courses.repository;

import com.se.courses.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 查询指定用户名密码的用户
     *
     * @param number
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.number=:num")
    User find(@Param("num") String number);
}
