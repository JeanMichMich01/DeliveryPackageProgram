package za.ac.cput.factory;

import za.ac.cput.domain.Sender;
import za.ac.cput.util.Helper;

public class SenderFactory {

    public static Sender createSender(String idSender, String firstName, String lastName, String phoneNumber){
        if(Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(phoneNumber))
            return null;

        if(phoneNumber.length() != 10 || phoneNumber.equals("0000000000"))
            return null;

        if(idSender.length() != 13)
            return null;

        return new Sender.Builder().setIdSender(idSender)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .build();
    }
}
