package za.ac.cput.domain;

import java.util.Date;

public class ShippingInfo {
    private int idOfficeStart;
    private int idOfficeEnd;
    private Date dateSend;
    private  Date dateArrived;

    private ShippingInfo() {}
    private ShippingInfo(Builder builder){
        this.idOfficeStart = builder.idOfficeStart;
        this.idOfficeEnd = builder.idOfficeEnd;
        this.dateSend = builder.dateSend;
        this.dateArrived = builder.dateArrived;
    }

    public int getIdOfficeStart() {
        return idOfficeStart;
    }

    public int getIdOfficeEnd() {
        return idOfficeEnd;
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
                ", idOfficeEnd=" + idOfficeEnd +
                ", dateSend=" + dateSend +
                ", dateArrived=" + dateArrived +
                '}';
    }
    public static class Builder{
        private int idOfficeStart;
        private int idOfficeEnd;
        private Date dateSend;
        private  Date dateArrived;

        public Builder setIdOfficeStart(int idOfficeStart) {
            this.idOfficeStart = idOfficeStart;
            return this;
        }

        public Builder setIdOfficeEnd(int idOfficeEnd) {
            this.idOfficeEnd = idOfficeEnd;
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
