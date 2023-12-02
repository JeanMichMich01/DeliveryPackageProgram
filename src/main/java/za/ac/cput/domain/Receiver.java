package za.ac.cput.domain;

public class Receiver {
    private String idReceiver;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private Receiver() {
    }

    private Receiver(Builder builder) {
        this.idReceiver = builder.idReceiver;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getIdReceiver() {
        return idReceiver;
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
        return "Receiver{" +
                "idReceiver=" + idReceiver +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", PhoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String idReceiver;
        private String firstName;
        private String lastName;
        private String phoneNumber;

        public Builder setIdReceiver(String idReceiver) {
            this.idReceiver = idReceiver;
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

        public Receiver build() {
            return new Receiver(this);
        }
    }
}

