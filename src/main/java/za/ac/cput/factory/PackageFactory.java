package za.ac.cput.factory;

import za.ac.cput.domain.Package;
import za.ac.cput.domain.StatuePackage;

public class PackageFactory {
    public static Package createPackage(int weight, StatuePackage status, boolean fragile){
        if (weight <=0){
            return null;
        }
        if(status==null){
            return null;
        }

        return new Package.Builder().setStatus(status)
                .setFragile(fragile)
                .setWeight(weight)
                .build();
    }

}
