package za.ac.cput.repository;

import za.ac.cput.domain.Receiver;
import za.ac.cput.factory.ReceiverFactory;
import za.ac.cput.util.Server;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ReceiverRepository implements IReceiverRepository{


    @Override
    public Receiver create(Receiver receiver) throws SQLException {
        String query = "INSERT INTO receiver " +
                "(id_receiver," +
                " first_name," +
                " last_name," +
                " phone_number) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, receiver.getIdReceiver());
            pstmt.setString(2, receiver.getFirstName());
            pstmt.setString(3, receiver.getLastName());
            pstmt.setString(4, receiver.getPhoneNumber());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return receiver;
    }

    @Override
    public Receiver read(String idReceiver) throws SQLException {
        String query = "SELECT * " +
                "FROM receiver " +
                "WHERE id_receiver = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, idReceiver);

            try (ResultSet resultSet = pstmt.executeQuery()){
                if (resultSet.next()){
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String phoneNumber = resultSet.getString("phone_number");

                    return ReceiverFactory.createReceiver(idReceiver, firstName, lastName, phoneNumber);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Receiver update(Receiver receiver) throws SQLException {
        String query = "UPDATE receiver " +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "phone_number = ? " +
                "WHERE id_receiver = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, receiver.getFirstName());
            pstmt.setString(2, receiver.getLastName());
            pstmt.setString(3, receiver.getPhoneNumber());
            pstmt.setString(4, receiver.getIdReceiver());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return receiver;
    }

    @Override
    public boolean delete(String idReceiver) throws SQLException {
        String query = "DELETE FROM receiver " +
                "WHERE id_receiver = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)){
            pstmt.setString(1, idReceiver);
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<Receiver> getAll() throws SQLException {
        List<Receiver> allReceiver = new ArrayList<>();
        String query = "SELECT * " +
                "FROM receiver";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery()){

                while (resultSet.next()){
                    String idReceiver = resultSet.getString("id_receiver");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String phoneNumber = resultSet.getString("phone_number");

                    Receiver receiver = ReceiverFactory.createReceiver(idReceiver, firstName, lastName, phoneNumber);

                    allReceiver.add(receiver);
                }
            }catch (SQLException e){
                throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
            }
        return allReceiver;
    }
}
