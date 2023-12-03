package za.ac.cput.repository;

import za.ac.cput.domain.Package;

import java.sql.SQLException;
import java.util.List;

public interface IPackageRepository extends IRepository<Package, Integer> {

    public List<Package> getAll() throws SQLException;
}