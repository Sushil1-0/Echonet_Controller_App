package jp.co.smartsolar.smartedge;

import jp.co.smartsolar.smartedge.component.Settings;
import jp.co.smartsolar.smartedge.service.MeasurementModuleService;
import jp.co.smartsolar.smartedge.service.SmartMeterService;
import jp.co.smartsolar.smartedge.service.SmartPowerDistributionBoardMeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jp.co.smartsolar.smartedge.component.ScheduledTasks;
/**
 * @package jp.co.smartsolar.smartedge
 * @Author subohaju
 * @date 5/8/2024
 */

public class Main {

    private static final Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");

    /**
     * ロガー.
     */
    private static final Logger eLogger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.ErrorLogService");


    public static void main(String[] args) throws Exception {
        Main mainInstance = new Main();
        mainInstance.run();
    }

    public void run() throws Exception {
        Settings settings = new Settings();
        settings.loadsettings();
        MeasurementModuleService measService = new MeasurementModuleService(); // Initialize measService
        measService.connect();
        SmartMeterService smService = new SmartMeterService();
        smService.connect();


        if (Boolean.parseBoolean(Settings.sPdbm)) {
            if (Settings.networkMode.equalsIgnoreCase("static")) {
                logger.info("smartPdbm = true, network_mode = static, enable spdbm");
                SmartPowerDistributionBoardMeterService spdbm = new SmartPowerDistributionBoardMeterService();
                spdbm.connect();
            }
        }
//         定期実行開始
        ScheduledTasks scheduledTasks = new ScheduledTasks();
        scheduledTasks.enable(true);

    }
}

