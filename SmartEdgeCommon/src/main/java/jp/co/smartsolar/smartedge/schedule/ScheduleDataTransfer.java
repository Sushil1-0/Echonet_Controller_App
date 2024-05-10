package jp.co.smartsolar.smartedge.schedule;

import java.rmi.Remote;

public interface ScheduleDataTransfer extends Remote {
    public void transfer(String data) throws Exception;
}
