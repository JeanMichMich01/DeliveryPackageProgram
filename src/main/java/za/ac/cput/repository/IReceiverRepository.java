package za.ac.cput.repository;
import za.ac.cput.domain.Receiver;

import java.sql.SQLException;
import java.util.List;

public interface IReceiverRepository extends IRepository<Receiver, String>{
    public List<Receiver> getAll() throws SQLException;
}
