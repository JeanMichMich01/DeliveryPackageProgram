package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.PostOffice;
import za.ac.cput.util.Helper;

public class PostOfficeFactory {

    public static PostOffice createPostOffice(String namePostOffice, int maxCapacity, Address address){

        if(Helper.isNullOrEmpty(namePostOffice))
            return null;

        if(maxCapacity <= 0)
            return null;

        if(address == null)
            return null;

        return new PostOffice.Builder().setNamePostOffice(namePostOffice)
                .setMaxCapacity(maxCapacity)
                .setAddress(address)
                .build();
    }

    public static PostOffice createPostOffice(int idPostOffice, String namePostOffice, int maxCapacity, Address address){

        if(Helper.isNullOrEmpty(namePostOffice))
            return null;

        if(maxCapacity <= 0 || idPostOffice <= 0)
            return null;

        if(address == null)
            return null;

        return new PostOffice.Builder().setIdPostOffice(idPostOffice)
                .setNamePostOffice(namePostOffice)
                .setMaxCapacity(maxCapacity)
                .setAddress(address)
                .build();
    }
}
