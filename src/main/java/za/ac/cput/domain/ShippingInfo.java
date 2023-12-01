package za.ac.cput.domain;

import java.util.Date;

public class ShippingInfo {
    private int idOfficeStart;
    private int getIdOfficeEnd;
    private Date dateSend;
    private  Date dateArrived;

    private ShippingInfo() {}
    private ShippingInfo(Builder builder){
        this.idOfficeStart = builder.idOfficeStart;
        this.getIdOfficeEnd = builder.getIdOfficeEnd;
        this.dateSend = builder.dateSend;
        this.dateArrived = builder.dateArrived;
    }

    public int getIdOfficeStart() {
        return idOfficeStart;
    }

    public int getGetIdOfficeEntd() {
        return getIdOfficeEnd;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public Date getDateArrived() {
        return dateArrived;
    }

    @Override
    public String toString() {
        return "ShippingInfo{" +
                "idOfficeStart=" + idOfficeStart +
                ", getIdOfficeEntd=" + getIdOfficeEnd +
                ", dateSend=" + dateSend +
                ", dateArrived=" + dateArrived +
                '}';
    }
    public static class Builder{
        private int idOfficeStart;
        private int getIdOfficeEnd;
        private Date dateSend;
        private  Date dateArrived;

        public Builder setIdOfficeStart(int idOfficeStart) {
            this.idOfficeStart = idOfficeStart;
            return this;
        }

        public Builder setGetIdOfficeEnd(int getIdOfficeEntd) {
            this.getIdOfficeEnd = getIdOfficeEntd;
            return this;
        }

        public Builder setDateSend(Date dateSend) {
            this.dateSend = dateSend;
            return this;
        }

        public Builder setDateArrived(Date dateArrived) {
            this.dateArrived = dateArrived;
            return this;
        }
        public ShippingInfo build(){
            return new ShippingInfo(this);
        }
    };
}
