package jp.co.smartsolar.smartedge.eoj;

import javax.xml.bind.DatatypeConverter;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

/**
 * @package jp.co.smartsolar.smartedge.eoj
 * @Author subohaju
 * @date 5/10/2024
 */

public class NodeProfileClassImpl extends NodeProfileClass {

    protected String makerCode = "000000";

    public String operatingStatus = "30";

    protected String identificationNumber = "FE00000000000000000000000000000001";

    protected static String EDT_DEFAULT_INDIVISUAL_IDENTIFICATION_INFO = "C000";

    protected static String individualIdentificationInfo = EDT_DEFAULT_INDIVISUAL_IDENTIFICATION_INFO;

    public NodeProfileClassImpl() {
        super(INSTANCE_CODE_GENERAL); // 一般ノードであることを指定
    }


    @Override
    public void initPorpMaps() {
        super.initPorpMaps();

        //		this.addMapGetProps(EPC_ERROR_CONTENT);  // 必須ではない
        this.addMapGetProps(EPC_INDIVIDUAL_IDENTIFICATION_INFO); // 必須ではない

        //		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
        this.addMapSetProps(EPC_INDIVIDUAL_IDENTIFICATION_INFO); // 必須ではない

    }


    @Override
    protected String getFromAppMakerCode() {
        return makerCode;
    }

    @Override
    protected String getFromAppOperationStatus() {
        return operatingStatus;
    }


    public void changeOperatingStatus(boolean status) {
        String sStat = (status ? "30" : "31");

        if (operatingStatus.equalsIgnoreCase(sStat))
            return;
        operatingStatus = sStat;
        try {
            inf().reqInfOperationStatus().send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected String getFromAppIdentificationNumber() {
        return identificationNumber;
    }


    protected void changeInstanceListNotification() {
        try {
            inf().reqInfInstanceListNotification().send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return;
    }


    @Override
    protected String getFromAppIndividualIdentificationInfo() {
        return individualIdentificationInfo;
    }


    @Override
    protected boolean setToAppIndividualIdentificationInfo(String edt) {
        if (check7thBitOfFirstByte(edt)) {
            individualIdentificationInfo = acceptIndividualIdentificationInfo(edt);
            return true;
        } else {
            return false;
        }
    }


    protected static boolean check7thBitOfFirstByte(String edt) {
        byte[] data = DatatypeConverter.parseHexBinary(edt);
        byte firstByte = data[0];
        if ((firstByte & 0x40) != 0) { // 0100 0000でマスク
            return true;
        } else {
            return false;
        }
    }


    protected static String acceptIndividualIdentificationInfo(String edt) {
        byte[] existing = DatatypeConverter.parseHexBinary(individualIdentificationInfo);
        byte[] byteArg = DatatypeConverter.parseHexBinary(edt);
        byte[] byteNew = new byte[2];
        byteNew[1] = byteArg[1];

        byte maskedExisting = (byte) (existing[0] & 0x80); // 1000 0000でマスク
        byte maskedArg = (byte) (byteArg[0] & 0x7f); // 0111 1111でマスク
        byteNew[0] = (byte) (maskedExisting | maskedArg);

        return ElUtil.binsToHexString(byteNew);
    }


    public void setDefaultIndividualIdentificationInfo() {
        individualIdentificationInfo = EDT_DEFAULT_INDIVISUAL_IDENTIFICATION_INFO;
    }
}
