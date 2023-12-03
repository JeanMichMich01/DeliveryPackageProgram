package za.ac.cput.repository;

import za.ac.cput.domain.Address;

import java.sql.SQLException;
import java.util.List;

public interface IAddressRepository extends IRepository<Address, String>{
    public List<Address> getAll() throws SQLException;
}
