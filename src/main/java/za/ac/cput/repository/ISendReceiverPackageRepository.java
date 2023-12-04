package za.ac.cput.repository;

import za.ac.cput.domain.SendReceiverPackage;

import java.sql.SQLException;
import java.util.List;

public interface ISendReceiverPackageRepository extends IRepository<SendReceiverPackage, String>{
    public List<SendReceiverPackage> getAll() throws SQLException;
}