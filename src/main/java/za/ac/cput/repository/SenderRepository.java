package za.ac.cput.repository;

import za.ac.cput.domain.Sender;
import za.ac.cput.factory.SenderFactory;
import za.ac.cput.util.Server;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SenderRepository implements ISenderRepository {

    @Override
    public Sender create (Sender sender) throws SQLException{
        String query = "INSERT INTO sender " +
                "(id_sender," +
                " first_name," +
                " last_name," +
                " phone_number) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, sender.getIdSender());
            pstmt.setString(2, sender.getFirstName());
            pstmt.setString(3, sender.getLastName());
            pstmt.setString(4, sender.getPhoneNumber());

            pstmt.executeUpdate();
        }catch(SQLException e){
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return sender;
    }

    @Override
    public Sender read(String idSender) throws SQLException{
        String query = "SELECT * " +
                "FROM sender " +
                "WHERE id_sender = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, idSender);

            try (ResultSet resultSet = pstmt.executeQuery()){
                if (resultSet.next()){
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String phoneNumber = resultSet.getString("phone_number");

                    return SenderFactory.createSender(idSender, firstName, lastName, phoneNumber);
                }
            }
        }catch(SQLException e){
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Sender update(Sender sender) throws SQLException{
        String query = "UPDATE sender " +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "phone_number = ? " +
                "WHERE id_sender = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, sender.getFirstName());
            pstmt.setString(2, sender.getLastName());
            pstmt.setString(3, sender.getPhoneNumber());
            pstmt.setString(4, sender.getIdSender());

            pstmt.executeUpdate();
        }catch(SQLException e){
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return sender;
    }

    @Override
    public boolean delete(String idSender) throws SQLException{
        String query = "DELETE FROM sender " +
                "WHERE id_sender = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, idSender);
            pstmt.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<Sender> getAll() throws SQLException{
        List<Sender> allSender = new ArrayList<>();
        String query = "SELECT * " +
                "FROM sender";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery()){

                while (resultSet.next()){
                    String idSender = resultSet.getString("id_sender");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String phoneNumber = resultSet.getString("phone_number");

                    Sender sender = SenderFactory.createSender(idSender, firstName, lastName, phoneNumber);

                    allSender.add(sender);
                }
        }catch(SQLException e){
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return allSender;
    }
}
