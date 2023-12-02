package za.ac.cput.factory;

import za.ac.cput.domain.SendReceiverPackage;
import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.util.Helper;

public class SendReceiverPackageFactory  {
    public static SendReceiverPackage createSendReceiverPackage(String idReceiver, String idSender, int idPackage, ShippingInfo shippingInfo){
        if(Helper.isNullOrEmpty(idReceiver)||Helper.isNullOrEmpty(idSender))
            return null;

        if(idPackage <= 0)
            return null;

        if(shippingInfo == null)
            return null;

        return new SendReceiverPackage.Builder().setIdReceiver(idReceiver)
                .setIdSender(idSender)
                .setIdPackage(idPackage)
                .setShippingInfo(shippingInfo)
                .build();
    }

}
