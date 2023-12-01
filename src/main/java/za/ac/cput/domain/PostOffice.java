package za.ac.cput.domain;

public class PostOffice {
    private int idPostOffice;
    private String namePostOffice;
    private int maxCapacity;
    private Address address;

    private PostOffice() {
    }

    private PostOffice(Builder builder){
        this.idPostOffice = builder.idPostOffice;
        this.namePostOffice = builder.namePostOffice;
        this.maxCapacity = builder.maxCapacity;
        this.address = builder.address;
    }

    public int getIdPostOffice() {
        return idPostOffice;
    }

    public String getNamePostOffice() {
        return namePostOffice;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PostOffice{" +
                "idPostOffice=" + idPostOffice +
                ", namePostOffice='" + namePostOffice + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", address=" + address +
                '}';
    }

    public static class Builder {
        private int idPostOffice;
        private String namePostOffice;
        private int maxCapacity;
        private Address address;

        public Builder setIdPostOffice(int idPostOffice) {
            this.idPostOffice = idPostOffice;
            return this;
        }

        public Builder setNamePostOffice(String namePostOffice) {
            this.namePostOffice = namePostOffice;
            return this;
        }

        public Builder setMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public PostOffice build(){return new PostOffice(this);}
    }
}
