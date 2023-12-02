package za.ac.cput.factory;

import za.ac.cput.domain.Receiver;
import za.ac.cput.util.Helper;

public class ReceiverFactory {
    public static Receiver createReceiver(String idReceiver, String firstName, String lastName, String phoneNumber){
        if(Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(phoneNumber))
            return null;

        if(phoneNumber.length() != 10 || phoneNumber.equals("0000000000"))
            return null;

        if(idReceiver.length() != 13)
            return null;

        return new Receiver.Builder().setIdReceiver(idReceiver)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .build();
    }
}
