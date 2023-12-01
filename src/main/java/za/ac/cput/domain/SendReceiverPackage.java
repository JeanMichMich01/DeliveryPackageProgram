package za.ac.cput.domain;

public class SendReceiverPackage {
    private int idSender;
    private int idReceiver;
    private int idPackage;
    private ShippingInfo shippingInfo;

    private SendReceiverPackage() {}

    private SendReceiverPackage(Builder builder) {
        this.idSender = builder.idSender;
        this.idReceiver = builder.idReceiver;
        this.idPackage = builder.idPackage;
        this.shippingInfo = builder.shippingInfo;
    }

    public int getIdSender() {
        return idSender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    @Override
    public String toString() {
        return "SendReceiverPackage{" +
                "idSender=" + idSender +
                ", idReceiver=" + idReceiver +
                ", idPackage=" + idPackage +
                ", shippingInfo=" + shippingInfo +
                '}';
    }

    public static class Builder {
        private int idSender;
        private int idReceiver;
        private int idPackage;
        private ShippingInfo shippingInfo;

        public Builder setIdSender(int idSender) {
            this.idSender = idSender;
            return this;
        }

        public Builder setIdReceiver(int idReceiver) {
            this.idReceiver = idReceiver;
            return this;
        }

        public Builder setIdPackage(int idPackage) {
            this.idPackage = idPackage;
            return this;
        }

        public Builder setShippingInfo(ShippingInfo shippingInfo) {
            this.shippingInfo = shippingInfo;
            return this;
        }

        public SendReceiverPackage build() {
            return new SendReceiverPackage(this);
        }
    }
}

