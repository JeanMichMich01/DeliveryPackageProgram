package za.ac.cput.domain;

public class Sender {
    private int idSender;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private Sender() {
    }

    private Sender(Builder builder) {
        this.idSender = builder.idSender;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
    }

    public int getIdSender() {
        return idSender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "idSender=" + idSender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class Builder {
        private int idSender;
        private String firstName;
        private String lastName;
        private String phoneNumber;

        public Builder setIdSender(int idSender) {
            this.idSender = idSender;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Sender build() {
            return new Sender(this);
        }
    }
}
