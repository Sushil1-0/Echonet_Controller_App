//package jp.co.smartsolar.smartedge.echonetlite.reportprocessor;
//
//import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
//import jp.co.smartsolar.smartedge.echonetlite.ElProp;
//import jp.co.smartsolar.smartedge.echonetlite.devices.PowerDistributionBoardMetering;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class PowerDistributionBoardMeteringReportProcessor extends PowerDistributionBoardMetering.ReportProcessor {
//
//    private static final Logger logger = LoggerFactory.getLogger(PowerDistributionBoardMeteringReportProcessor.class);
//
//    private static Map<Long, Double> dataMapChannel3 = new LinkedHashMap<Long, Double>();
//    private static Map<Long, Double> dataMapChannel4 = new LinkedHashMap<Long, Double>();
//    private static Map<Long, Double> dataMapChannel5 = new LinkedHashMap<Long, Double>();
//    private static Map<Long, Double> dataMapChannel6 = new LinkedHashMap<Long, Double>();
//    private static Map<Long, Double> dataMapChannel7 = new LinkedHashMap<Long, Double>();
//    
//    public Map<Long, Double> getDataMapChannel3() {
//        return dataMapChannel3;
//    }
//
//    public Map<Long, Double> getDataMapChannel4() {
//        return dataMapChannel4;
//    }
//
//    public Map<Long, Double> getDataMapChannel5() {
//        return dataMapChannel5;
//    }
//
//    public Map<Long, Double> getDataMapChannel6() {
//        return dataMapChannel6;
//    }
//
//    public Map<Long, Double> getDataMapChannel7() {
//        return dataMapChannel7;
//    }
//
//    protected void clearDataMaps() {
//        logger.info("reportProcessor: Clearing data maps");
//        dataMapChannel3.clear();
//        dataMapChannel4.clear();
//        dataMapChannel5.clear();
//        dataMapChannel6.clear();
//        dataMapChannel7.clear();
//    }
//
//    private Date getCurrentTimestamp() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
//        String currentTimestamp = dateFormat.format(new Date());
//        return dateFormat.parse(currentTimestamp);
//    }
//
//    @Override
//    public  void onGetMeasurementChannelValue3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
//        logger.info("Channel 3 value acquired: [ " + objProp.getStrEdt() + " ]");
//
//        if (isSuccess) {
//            String edt = objProp.getStrEdt();
//            int power = Integer.parseInt(edt, 16);
//            Double powerValue = ((double) power);	//		/1000
//
//            try {
//                dataMapChannel3.put(getCurrentTimestamp().getTime(), powerValue);
//                for(Map.Entry<Long, Double> entry: dataMapChannel3.entrySet()) {
//                	System.out.println("Key = " + entry.getKey());
//                	System.out.println("Value = " + entry.getValue());
//                }
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            logger.warn("onGetMeasurementChannelValue3, isSuccess: false");
//        }
//    }
//
//    @Override
//    public  void onGetMeasurementChannelValue4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
//        logger.info("Channel 4 value acquired: [ " + objProp.getStrEdt() + " ]");
//
//        if (isSuccess) {
//            String edt = objProp.getStrEdt();
//            int power = Integer.parseInt(edt, 16);
//            Double powerValue = ((double) power);		//		/1000
//
//            try {
//                dataMapChannel4.put(getCurrentTimestamp().getTime(), powerValue);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            logger.warn("onGetMeasurementChannelValue4, isSuccess: false");
//        }
//    }
//
//    @Override
//    public  void onGetMeasurementChannelValue5(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
//        logger.info("Channel 5 value acquired: [ " + objProp.getStrEdt() + " ]");
//
//        if (isSuccess) {
//            String edt = objProp.getStrEdt();
//            int power = Integer.parseInt(edt, 16);
//            Double powerValue = ((double) power);	//		/1000
//
//            try {
//                dataMapChannel5.put(getCurrentTimestamp().getTime(), powerValue);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            logger.warn("onGetMeasurementChannelValue5, isSuccess: false");
//        }
//    }
//
//    @Override
//    public  void onGetMeasurementChannelValue6(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
//        logger.info("Channel 6 value acquired: [ " + objProp.getStrEdt() + " ]");
//
//        if (isSuccess) {
//            String edt = objProp.getStrEdt();
//            int power = Integer.parseInt(edt, 16);
//            Double powerValue = ((double) power);	//		/1000
//
//            try {
//                dataMapChannel6.put(getCurrentTimestamp().getTime(), powerValue);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            logger.warn("onGetMeasurementChannelValue6, isSuccess: false");
//        }
//    }
//
//    @Override
//    public  void onGetMeasurementChannelValue7(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
//        logger.info("Channel 7 value acquired: [ " + objProp.getStrEdt() + " ]");
//
//        if (isSuccess) {
//            String edt = objProp.getStrEdt();
//            int power = Integer.parseInt(edt, 16);
//            Double powerValue = ((double) power);	//		/1000
//
//            try {
//                dataMapChannel7.put(getCurrentTimestamp().getTime(), powerValue);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            logger.warn("onGetMeasurementChannelValue7, isSuccess: false");
//        }
//    }
//
//}
