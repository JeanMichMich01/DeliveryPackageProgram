package za.ac.cput.repository;

import za.ac.cput.domain.PostOffice;

import java.sql.SQLException;
import java.util.List;

public interface IPostOfficeRepository extends IRepository<PostOffice, String>{
    public List<PostOffice> getAll() throws SQLException;
}
