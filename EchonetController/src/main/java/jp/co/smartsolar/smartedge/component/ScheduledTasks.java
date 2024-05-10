//package jp.co.smartsolar.smartedge.component;
//
//import jp.co.smartsolar.smartedge.service.MeasurementModuleService;
//
//import java.time.Duration;
//import java.time.ZonedDateTime;
//import java.time.ZoneId;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
///**
// * @package jp.co.smartsolar.smartedge.component
// * @Author subohaju
// * @date 5/10/2024
// */
//public class ScheduledTasks {
//    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10); // Adjust the number of threads as needed
//
//    private boolean isEnabled = false;
//    MeasurementModuleService measurementModuleService = new MeasurementModuleService();
//    Settings settings = new Settings();
//
//    public void enable (boolean enable){
//        isEnabled = enable;
//    }
//    public void scheduleTasks() {
//        // Schedule RunEveryMinute
//        String cronExpressionMinute = "0 * * * * *";
//        long minutes = Long.parseLong(cronExpressionMinute.split(" ")[0]);
//        Duration durationMinute = Duration.ofMinutes(minutes);
//        ZonedDateTime nowMinute = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
//        ZonedDateTime nextExecutionTimeMinute = nowMinute.plus(durationMinute);
//        executor.scheduleAtFixedRate(this::RunEveryMinute, nextExecutionTimeMinute.toEpochSecond(), durationMinute.toMillis(), TimeUnit.MILLISECONDS);
//
//        // Schedule RunEveryHour
//        String cronExpressionHour = "0 0 * * * *"; // Note: This is actually every minute, not every hour
//        long hours = Long.parseLong(cronExpressionHour.split(" ")[0]);
//        Duration durationHour = Duration.ofHours(hours);
//        ZonedDateTime nowHour = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
//        ZonedDateTime nextExecutionTimeHour = nowHour.plus(durationHour);
//        executor.scheduleAtFixedRate(this::RunEveryHour, nextExecutionTimeHour.toEpochSecond(), durationHour.toMillis(), TimeUnit.MILLISECONDS);
//
//    }
//    public void RunEveryMinute() {
//        if (isEnabled) {
//            measurementModuleService.excuteByEveryMinute();
//
//            if (SmartMaterService.isSmIdPassPresent) {
//                smService.excuteByEveryMinute();
//            }
//
//            // spdbm read
//            if (Boolean.parseBoolean(settings.getSmartPdbm()) && settings.getNetworkMode().equalsIgnoreCase("static")) {
//                spdbm.excuteByEveryMinute();
//            }
//
//        }
//    }
//    public void RunEveryHour() {		// runs every min ~ naming convention wrong
//        if (isEnabled) {
//            measurementModuleService.excuteByEveryHour();
//
//            if (SmartMaterService.isSmIdPassPresent) {
//                smService.excuteByEveryHour();
//            }
//
//            // spdbm save to Db
//            if (Boolean.parseBoolean(settings.getSmartPdbm()) && settings.getNetworkMode().equalsIgnoreCase("static")) {
//                spdbm.excuteByEveryHour();
//            }
//        }
//
//    }
//
//}