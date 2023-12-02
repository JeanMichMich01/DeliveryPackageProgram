package za.ac.cput.factory;

import za.ac.cput.domain.Package;

public class PackageFactory {
    public static Package createPackage(int weight, boolean fragile){
        if (weight <=0){
            return null;
        }

        return new Package.Builder().setFragile(fragile)
                .setWeight(weight)
                .build();
    }

}
