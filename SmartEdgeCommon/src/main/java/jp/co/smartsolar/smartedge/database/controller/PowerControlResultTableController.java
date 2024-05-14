package jp.co.smartsolar.smartedge.database.controller;

import jp.co.smartsolar.smartedge.database.entity.PowerControlResult;

import java.sql.SQLException;
import java.util.List;

/**
 * @package jp.co.smartsolar.smartedge.database.controller
 * @Author subohaju
 * @date 5/14/2024
 */
public class PowerControlResultTableController extends BaseTableController<PowerControlResult>{
    public PowerControlResultTableController(){
        super();
    }
    public List<PowerControlResult> selectAll() throws SQLException{
        return getEntityManager().createNamedQuery("PowerControlResult.findAll",PowerControlResult.class).getResultList();
    }
}
