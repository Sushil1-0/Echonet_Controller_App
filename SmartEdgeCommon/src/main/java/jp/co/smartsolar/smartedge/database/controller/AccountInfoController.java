package jp.co.smartsolar.smartedge.database.controller;

import java.util.List;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jp.co.smartsolar.smartedge.database.entity.Account;
import jp.co.smartsolar.smartedge.util.DatabaseEntityManager;

public class AccountInfoController extends BaseTableController<Account>  {
	  protected final static Logger log = LoggerFactory.getLogger(AccountInfoController.class);

    /** 環境定義. */
    public enum Enviroment {
        /** Edgeモジュール側. */
        forEdge(1),
        /** 測定モジュール側. */
        forMeas(2);

        private Integer id;

        Enviroment(Integer id) {
            this.id = id;
        }
        public Integer getId() {
            return this.id;
        }
    }

	public AccountInfoController() {
		super();
	}
	
	public List<Account> findAll() {
		List<Account> res = null;
		EntityManager em = DatabaseEntityManager.getNewEntityManager();
		//        return getEntityManager().createNamedQuery("Account.findAll",Account.class)
		try {
			res =  em.createNamedQuery("Account.findAll", Account.class).getResultList();
		} catch (Exception e) {
			log.info("Exception List<Account> findAll " +  e.getMessage());
		} finally {
			em.close();
		}
		return res;
	}
      
	}
	
