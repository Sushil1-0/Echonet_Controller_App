package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;

import jp.co.smartsolar.smartedge.database.entity.PowerCatalog;
import jp.co.smartsolar.smartedge.util.DatabaseEntityManager;

/**
 * 基底データベーステーブル操作クラス.
 *
 * @author ubiq
 *
 */
abstract class BaseTableController<T> {
    private static EntityManager em = null;

    public BaseTableController() {
        em = DatabaseEntityManager.getEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void insert(T entitiy) throws SQLException {
        em.persist(entitiy);
    }

    public void update(T entitiy) throws SQLException {
        em.merge(entitiy);
    }

    protected List<T> selectAll(Class<T> clazz) throws SQLException {
        String name = this.getClass().getName() + ".findAll";
        return em.createNamedQuery(name, clazz).getResultList();
    }

    // insertのみ
    public void saveAll(List<T> list) throws SQLException {
        for (T it : list) {
            em.persist(it);
        }
    }    

    //added 20210215
    public void update(T entity, EntityManager em2) throws SQLException{
    	em2.merge(entity);
	}
    
    public void update(List<PowerCatalog> saveList, EntityManager em2) throws SQLException{
    	for (PowerCatalog it : saveList) {
    		em2.merge(it);
    	}
    }
    
	public void saveAll(List<PowerCatalog> saveList, EntityManager em2) throws SQLException {
		for (PowerCatalog it : saveList) {
			em2.persist(it);
		}		
	}
    
	public void remove(List<PowerCatalog> removeList, EntityManager em2) throws SQLException {
		for (PowerCatalog it : removeList) {
			em2.remove(it);
		}		
    }
	
    public void remove(T entity) throws SQLException {
        em.remove(entity);
    }
    
    public void insert(T entity, EntityManager em2) throws SQLException {
        em2.persist(entity);
    }
}
