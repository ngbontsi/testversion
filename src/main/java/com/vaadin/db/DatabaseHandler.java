package com.vaadin.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    public static Object insertEntity(Object obj) {
        EntityManager entityManager = getEntityManager();
        entityManager.persist(obj);
        entityManager.flush();
        closeConection(entityManager);
        return obj;
    }

    public static Object findEntity(Object obj) {
        EntityManager entityManager = getEntityManager();
        obj = entityManager.find(obj.getClass(), 1);
        closeConection(entityManager);
        return obj;
    }

    public static Object updateEntity(Object obj) {
        EntityManager entityManager = getEntityManager();

        Object dbObj = entityManager.find(obj.getClass(),1);
        if(dbObj== null){
            insertEntity(obj);
        }else {
            entityManager.detach(obj);
           entityManager.merge(obj);
        }
        entityManager.flush();
        closeConection(entityManager);
        return obj;
    }


    public static List<?> findAll(Object obj) {
        EntityManager entityManager = getEntityManager();
        List<Object> list = entityManager.createQuery("Select t from " + obj.getClass().getSimpleName() + " t").getResultList();

        closeConection(entityManager);
        return list;
    }

    public static List<?> findAll(String query) {
        EntityManager entityManager = getEntityManager();
        List<?> list1 = entityManager.createQuery(query).getResultList();
        closeConection(entityManager);
        return list1;
    }

    public static Object findEntity(String query) {
        EntityManager entityManager = getEntityManager();
        Object output = new Object();
        try{
            output =entityManager.createQuery(query).getSingleResult();
        }catch (NoResultException e){
            output = null;
        }

        closeConection(entityManager);
        return output;
    }

    private static void closeConection(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static EntityManager getEntityManager() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    public static boolean delete(Object obj){

        EntityManager entityManager = getEntityManager();
        entityManager.remove(obj);
        closeConection(entityManager);
        return true;
    }

}
