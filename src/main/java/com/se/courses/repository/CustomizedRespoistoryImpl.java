package com.se.courses.repository;


import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomizedRespoistoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomizedRepoistory<T, ID> {

    private EntityManager em;

    public CustomizedRespoistoryImpl(JpaEntityInformation entityInformation,
                                     EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public void refresh(T t) {
        em.refresh(t);
    }

    @Override
    public void saveBatch(List<T> entities) {
        for (int i = 0; i < entities.size(); i++) {
            em.persist(entities.get(i));
            if ((i % 20) == 0) {
                em.flush();
            }
        }
    }
}
