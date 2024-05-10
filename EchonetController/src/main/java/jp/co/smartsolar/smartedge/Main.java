package jp.co.smartsolar.smartedge;

import jp.co.smartsolar.smartedge.component.Settings;
import jp.co.smartsolar.smartedge.service.MeasurementModuleService;

/**
 * @package jp.co.smartsolar.smartedge
 * @Author subohaju
 * @date 5/8/2024
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Main mainInstance = new Main();
        mainInstance.run();
    }

    public void run() throws Exception {
        Settings settings = new Settings();
        settings.loadsettings();
        MeasurementModuleService measService = new MeasurementModuleService(); // Initialize measService
        measService.connect();
    }
}

