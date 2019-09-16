package com.se.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * spring-data-jpa不提供JPA的refresh()方法<br/>
 * 看源码saveAll()是假的，自己实现提高效率
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomizedRepoistory<T, ID> extends JpaRepository<T, ID> {
    void refresh(T t);

    void saveBatch(List<T> entities);
}