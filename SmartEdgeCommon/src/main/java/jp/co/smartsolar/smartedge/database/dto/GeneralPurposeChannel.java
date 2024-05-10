package jp.co.smartsolar.smartedge.database.dto;

import java.util.List;

public class GeneralPurposeChannel {

    private List<CtChannel> channels;

    public List<CtChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<CtChannel> channels) {
        this.channels = channels;
    }

    public static class CtChannel {

        private String channelName;
        private Integer channelId;
        private Integer channelVoltage;

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public Integer getChannelId() {
            return channelId;
        }

        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }

        public Integer getChannelVoltage() {
            return channelVoltage;
        }

        public void setChannelVoltage(Integer channelVoltage) {
            this.channelVoltage = channelVoltage;
        }

        @Override
        public String toString() {
            return "CtChannel{" +
                    "channelName='" + channelName + '\'' +
                    ", channelId=" + channelId +
                    ", channelVoltage=" + channelVoltage +
                    '}';
        }
    }

}
