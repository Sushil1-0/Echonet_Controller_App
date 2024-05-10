package jp.co.smartsolar.smartedge.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseEntityManager {

    private static final String               PERSISTENCE_UNIT_NAME = "SmartEdgeCommon";
    private static final EntityManagerFactory emFactoryObj;
    private static final EntityManager        emObject;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        emObject = emFactoryObj.createEntityManager();
    }

    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emObject;
    }
    
    // 新規EntityManagerインスタンスを生成する
    public static EntityManager getNewEntityManager() {
    	return emFactoryObj.createEntityManager();
    }
}
