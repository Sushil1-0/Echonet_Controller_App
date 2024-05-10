package jp.co.smartsolar.smartedge.eoj.reportprocessor;

import java.sql.SQLException;

/**
 * @package jp.co.smartsolar.smartedge.eoj.reportprocessor
 * @Author subohaju
 * @date 5/10/2024
 */


public interface ReportProcessorInterface {
    void writeDatabase() throws SQLException;
}
