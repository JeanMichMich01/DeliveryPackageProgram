package za.ac.cput.domain;

public class Package {
    private int idPackage;
    private int weight;
    private boolean fragile;
    private StatuePackage status;

    private Package() {
    }

    private Package(Builder builder) {
        this.idPackage = builder.idPackage;
        this.weight = builder.weight;
        this.fragile = builder.fragile;
        this.status = builder.status;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isFragile() {
        return fragile;
    }

    public StatuePackage getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Package{" +
                "idPackage=" + idPackage +
                ", weight=" + weight +
                ", fragile=" + fragile +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private int idPackage;
        private int weight;
        private boolean fragile;
        private StatuePackage status;

        public Builder setIdPackage(int idPackage) {
            this.idPackage = idPackage;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder setFragile(boolean fragile) {
            this.fragile = fragile;
            return this;
        }

        public Builder setStatus(StatuePackage status) {
            this.status = status;
            return this;
        }

        public Package build() {
            return new Package(this);
        }
    }
}
