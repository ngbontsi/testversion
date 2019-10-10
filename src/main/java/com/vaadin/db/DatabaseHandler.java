package com.vaadin.db;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    public static Object insertEntity(Object obj){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return obj;
    }

    public static Object findEntity(Object obj){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        obj =entityManager.find(obj.getClass(),1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return obj;
    }

    public static Object updateEntity(Object obj){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Object dbObj = entityManager.find(obj.getClass(),1);
        if(dbObj== null){
            insertEntity(obj);
        }else {
           entityManager.merge(obj);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return obj;
    }


    public static List<?> findAll(Object obj){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<Object> list = entityManager.createQuery("Select t from "+obj.getClass().getSimpleName()+" t").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return list;
    }

    public static List<?> findAll(String query){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<?> list1 = new ArrayList<>();
         list1 = entityManager.createQuery(query).getResultList();
        List<?> list = entityManager.createQuery(query).getResultList();
        System.out.println("Displaying the list :"+list);
        Method[] methods = list.get(0).getClass().getMethods();
        entityManager.getTransaction().commit();
        entityManager.close();
        return list1;
    }

}
