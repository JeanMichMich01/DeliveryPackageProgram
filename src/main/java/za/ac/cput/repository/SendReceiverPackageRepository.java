package za.ac.cput.repository;

import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.domain.SendReceiverPackage;
import za.ac.cput.factory.SendReceiverPackageFactory;
import za.ac.cput.util.Server;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class  SendReceiverPackageRepository implements ISendReceiverPackageRepository{

    @Override
    public SendReceiverPackage create(SendReceiverPackage sendReceiverPackage) throws SQLException {
        String query = "INSERT INTO send_receiver_package " +
                "(id_sender," +
                " id_receiver," +
                " id_package," +
                " shipping_info)" +
                "VALUES (?, ?, ?,? )";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, sendReceiverPackage.getIdSender());
            pstmt.setString(2, sendReceiverPackage.getIdReceiver());
            pstmt.setInt(3, sendReceiverPackage.getIdPackage());
            pstmt.setString(4,sendReceiverPackage.getShippingInfo().getDateSend());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return sendReceiverPackage;
    }

    @Override
    public SendReceiverPackage read(String idPackage) throws SQLException {
        String query = "SELECT * " +
                "FROM send_receiver_package " +
                "WHERE id_package = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(idPackage));

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String idSender = resultSet.getString("id_sender");
                    String idReceiver = resultSet.getString("id_receiver");
                    String dateSend = resultSet.getString("shipping_info");

                    ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();
                    ShippingInfo shippingInfo = shippingInfoRepository.read(dateSend);

                    return SendReceiverPackageFactory.createSendReceiverPackage(idSender, idReceiver, Integer.parseInt(idPackage), shippingInfo);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }

    @Override
    public SendReceiverPackage update(SendReceiverPackage sendReceiverPackage) throws SQLException {
        String query = "UPDATE send_receiver_package " +
                "SET id_sender = ?, " +
                "id_receiver = ? " +
                "WHERE id_package = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, sendReceiverPackage.getIdSender());
            pstmt.setString(2, sendReceiverPackage.getIdReceiver());
            pstmt.setInt(3, sendReceiverPackage.getIdPackage());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return sendReceiverPackage;
    }

    @Override
    public boolean delete(String idPackage) throws SQLException {
        String query = "DELETE FROM send_receiver_package " +
                "WHERE id_package = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(idPackage));

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<SendReceiverPackage> getAll() throws SQLException {
        List<SendReceiverPackage> allPostOffices = new ArrayList<>();
        String query = "SELECT * " +
                "FROM send_receiver_package";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                String idSender = resultSet.getString("id_sender");
                String idReceiver = resultSet.getString("id_receiver");
                int idPackage = resultSet.getInt("id_package");
                String dateSend = resultSet.getString("shipping_info");

                ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();
                ShippingInfo shippingInfo = shippingInfoRepository.read(dateSend);

                SendReceiverPackage postOffice = SendReceiverPackageFactory.createSendReceiverPackage(idSender, idReceiver, idPackage, shippingInfo);
                allPostOffices.add(postOffice);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT(all) query: " + e.getMessage());
        }
        return allPostOffices;
    }
}
