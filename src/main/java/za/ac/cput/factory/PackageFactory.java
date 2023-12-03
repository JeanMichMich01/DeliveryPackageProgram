package za.ac.cput.factory;

import za.ac.cput.domain.Package;
import za.ac.cput.domain.StatuePackage;

public class PackageFactory {
    public static Package createPackage(int weight, boolean fragile ){
        if (weight <=0){
            return null;
        }

        return new Package.Builder().setFragile(fragile)
                .setWeight(weight)
                .build();
    }

    public static Package createPackage(int idPackage, int weight, boolean fragile, StatuePackage status){
        if (weight <=0){
            return null;
        }

        return new Package.Builder().setFragile(fragile)
                .setWeight(weight)
                .setIdPackage(idPackage)
                .setStatus(status)
                .build();
    }

}
