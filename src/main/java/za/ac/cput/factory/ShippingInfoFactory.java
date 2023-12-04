package za.ac.cput.factory;

import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.util.Helper;

import java.util.Date;

public class ShippingInfoFactory {
    public static ShippingInfo createShippingInfo(int idOfficeStart, int idOfficeEnd, String dateSend){
        if (idOfficeStart <= 0 || idOfficeEnd <= 0)
            return null;

        if (Helper.isNullOrEmpty(dateSend))
            return null;

        return new ShippingInfo.Builder().setIdOfficeStart(idOfficeStart)
                .setIdOfficeEnd(idOfficeEnd)
                .setDateSend(dateSend)
                .build();
    }
}