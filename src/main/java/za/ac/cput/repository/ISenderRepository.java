package za.ac.cput.repository;
import za.ac.cput.domain.Sender;

import java.sql.SQLException;
import java.util.List;

public interface ISenderRepository extends IRepository<Sender, String>{
    public List<Sender> getAll() throws SQLException;

}
