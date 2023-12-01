package za.ac.cput.domain;

public class Address {
    private int numberStreet;
    private String nameStreet;
    private String zipCode;
    private String nameCity;

    private Address() {
    }

    private Address(Builder builder) {
        this.numberStreet = builder.numberStreet;
        this.nameStreet = builder.nameStreet;
        this.zipCode = builder.zipCode;
        this.nameCity = builder.nameCity;
    }

    public int getNumberStreet() {
        return numberStreet;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getNameCity() {
        return nameCity;
    }

    @Override
    public String toString() {
        return "Address{" +
                "numberStreet=" + numberStreet +
                ", nameStreet='" + nameStreet + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", nameCity='" + nameCity + '\'' +
                '}';
    }

    public static class Builder {
        private int numberStreet;
        private String nameStreet;
        private String zipCode;
        private String nameCity;

        public Builder setNumberStreet(int numberStreet) {
            this.numberStreet = numberStreet;
            return this;
        }

        public Builder setNameStreet(String nameStreet) {
            this.nameStreet = nameStreet;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder setNameCity(String nameCity) {
            this.nameCity = nameCity;
            return this;
        }

        public Address build() { return new Address(this);}
    }
}
