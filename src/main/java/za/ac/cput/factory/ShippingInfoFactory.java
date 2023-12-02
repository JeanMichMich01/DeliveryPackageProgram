package za.ac.cput.factory;
import za.ac.cput.domain.PostOffice;
import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.util.Helper;
import java.util.Date;
public class ShippingInfoFactory {
public static ShippingInfo createShippingInfo(int idOfficeStart, int idOfficeEnd, Date dateSend, Date dateArrived){
    if (idOfficeStart <= 0)
        return null;

    if (idOfficeEnd <= 0)
        return null;

    if (dateSend==null||dateArrived==null)
        return null;

    return new ShippingInfo.Builder().setIdOfficeStart(idOfficeStart)
            .setIdOfficeEnd(idOfficeEnd)
            .setDateSend(dateSend)
            .setDateArrived(dateArrived)
            .build();
    }
}
