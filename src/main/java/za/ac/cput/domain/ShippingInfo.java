package za.ac.cput.domain;

import java.util.Date;

public class ShippingInfo {
    private int idOfficeStart;
    private int idOfficeEnd;
    private String dateSend;

    public ShippingInfo() {}

    private ShippingInfo(Builder builder){
        this.idOfficeStart = builder.idOfficeStart;
        this.idOfficeEnd = builder.idOfficeEnd;
        this.dateSend = builder.dateSend;
    }

    public int getIdOfficeStart() {
        return idOfficeStart;
    }

    public int getIdOfficeEnd() {
        return idOfficeEnd;
    }

    public String getDateSend() {
        return dateSend;
    }


    @Override
    public String toString() {
        return "ShippingInfo{" +
                "idOfficeStart=" + idOfficeStart +
                ", idOfficeEnd=" + idOfficeEnd +
                ", dateSend=" + dateSend +
                '}';
    }
    public static class Builder{
        private int idOfficeStart;
        private int idOfficeEnd;
        private String dateSend;

        public Builder setIdOfficeStart(int idOfficeStart) {
            this.idOfficeStart = idOfficeStart;
            return this;
        }

        public Builder setIdOfficeEnd(int idOfficeEnd) {
            this.idOfficeEnd = idOfficeEnd;
            return this;
        }

        public Builder setDateSend(String dateSend) {
            this.dateSend = dateSend;
            return this;
        }
        public ShippingInfo build(){
            return new ShippingInfo(this);
        }
    };
}