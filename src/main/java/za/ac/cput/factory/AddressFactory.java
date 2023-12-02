package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {
    public static Address createAddress(int numberStreet, String nameStreet, String zipCode, String nameCity){
        if(Helper.isNullOrEmpty(nameStreet)  || Helper.isNullOrEmpty(nameCity))
            return null;

        if(numberStreet <= 0)
            return null;

        if(zipCode.length() != 4 || zipCode.equals("0000") || Helper.isNullOrEmpty(zipCode))
            return null;

        return new Address.Builder().setNumberStreet(numberStreet)
                .setNameStreet(nameStreet)
                .setZipCode(zipCode)
                .setNameCity(nameCity)
                .build();
    }
}
