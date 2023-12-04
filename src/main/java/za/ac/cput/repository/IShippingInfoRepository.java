package za.ac.cput.repository;
import za.ac.cput.domain.ShippingInfo;

import java.sql.SQLException;
import java.util.List;

public interface IShippingInfoRepository extends IRepository<ShippingInfo, String> {
    public List<ShippingInfo> getAll() throws SQLException;
}