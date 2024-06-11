package com.ohgiraffers.jpql.section05.join;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Menu> selectInnerJoin() {

        String jpql = "SELECT m FROM section05Menu m JOIN m.category c";
        List<Menu> menuList = manager.createQuery(jpql, Menu.class).getResultList();

        return menuList;
    }

    public List<Object[]> selectOutterJoin() {

        String jqpl = "SELECT m.menuName, c.categoryName" +
                " FROM section05Menu m" +
                " RIGHT JOIN m.category c" +
                " ORDER BY m.category.categoryCode";

        List<Object[]> menuList = manager.createQuery(jqpl).getResultList();

        return menuList;
    }

    public List<Object[]> selectCollectionJoin() {

        String jqpl = "SELECT m.menuName, c.categoryName" +
                " FROM section05Category c" +
                " LEFT JOIN c.menuList m";

        List<Object[]> categoryList = manager.createQuery(jqpl).getResultList();

        return categoryList;
    }

    // 엔티티와 관련된 연관 엔티티를 한 번의 쿼리로 함께 조회하는 방법
    public List<Menu> selectFetchJoin() {

        String jpql = "SELECT m FROM section05Menu m JOIN FETCH m.category c";
        List<Menu> menuList = manager.createQuery(jpql,Menu.class).getResultList();

        return menuList;
    }
}
