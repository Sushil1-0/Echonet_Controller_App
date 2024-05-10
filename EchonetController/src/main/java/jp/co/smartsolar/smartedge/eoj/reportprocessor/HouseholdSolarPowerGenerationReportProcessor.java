package jp.co.smartsolar.smartedge.eoj.reportprocessor;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jp.co.smartsolar.smartedge.database.controller.DeviceMasterTableController;
import jp.co.smartsolar.smartedge.database.controller.EdgeSolarPowerTableController;
import jp.co.smartsolar.smartedge.database.entity.DeviceMaster;
import jp.co.smartsolar.smartedge.database.entity.EdgeSolarPower;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.devices.HouseholdSolarPowerGenerationClass.ReportProcessor;


/**
 * @package jp.co.smartsolar.smartedge.eoj.reportprocessor
 * @Author subohaju
 * @date 5/10/2024
 */

public class HouseholdSolarPowerGenerationReportProcessor extends ReportProcessor implements ReportProcessorInterface {

    private Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.echonetlite.sercice.LogService");

    public EdgeSolarPowerTableController edgeSolarPowerTableController = new EdgeSolarPowerTableController();

    public DeviceMasterTableController deviceMasterTableController = new DeviceMasterTableController();

    private Map<Long, EdgeSolarPower> edgeSolarPowerMap = new ConcurrentHashMap<Long, EdgeSolarPower>();

    @Override
    public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        logger.info("太陽光発電 - 動作状態受信 : {}", objProp.getStrEdt());
    }

    @Override
    public void onGetOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        logger.info("太陽光発電 - 出力制御設定１受信 : {}", objProp.getStrEdt());
    }

    @Override
    public void onGetMeasuredCumulativeAmountOfEnergyGenerated(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess){
        logger.info("太陽光発電 - 積算発電電力量計測値受信 : {}", objProp.getStrEdt());
        if (isSuccess) {
            int deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            int edtInt = Integer.parseInt(edt, 16);

            DeviceMaster deviceMaster = null;
            try {
                deviceMaster = deviceMasterTableController.findById(deviceId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (deviceMaster!= null) {
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
                EdgeSolarPower entity = new EdgeSolarPower();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setElectricVol((double) edtInt);
                entity.setDeviceMaster(deviceMaster);
                edgeSolarPowerMap.put(now.getTime(), entity);
            }
        } else {
            logger.warn("onGetMeasuredCumulativeAmountOfEnergyGenerated : isSuccess=false");
        }
    }


    @Override
    public void writeDatabase()  {
        Collection<EdgeSolarPower> collection = edgeSolarPowerMap.values();
        try {
            edgeSolarPowerTableController.saveAll(collection.stream().toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        edgeSolarPowerMap.clear();
    }
}
