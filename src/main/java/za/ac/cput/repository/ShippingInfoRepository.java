package za.ac.cput.repository;
import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.factory.ShippingInfoFactory;
import za.ac.cput.util.Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ShippingInfoRepository implements IShippingInfoRepository{

    @Override
    public ShippingInfo create( ShippingInfo shippingInfo) throws SQLException {
        String query = "INSERT INTO shipping_info " +
                "(id_office_end," +
                " date_send," +
                "id_office_start)" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, shippingInfo.getIdOfficeEnd());
            pstmt.setString(2, shippingInfo.getDateSend());
            pstmt.setInt(3, shippingInfo.getIdOfficeStart());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return shippingInfo;
    }




    @Override
    public ShippingInfo read(String dateSend) throws SQLException {
        String query = "SELECT * " +
                "FROM shipping_info " +
                "WHERE date_send = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1,  dateSend);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int idOfficeStart = resultSet.getInt("id_office_start");
                    int idOfficeEnd = resultSet.getInt("id_office_end");

                    return ShippingInfoFactory.createShippingInfo(idOfficeStart, idOfficeEnd, dateSend);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ShippingInfo update(ShippingInfo shippingInfo) throws SQLException {
        String query = "UPDATE shipping_info " +
                "SET id_office_start = ?, " +
                "id_office_end = ? "+
                "WHERE date_send = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, shippingInfo.getIdOfficeStart());
            pstmt.setInt(2, shippingInfo.getIdOfficeEnd());
            pstmt.setString(3, shippingInfo.getDateSend());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return shippingInfo;
    }


    @Override
    public boolean delete(String dateSend) throws SQLException {
        String query = "DELETE FROM shipping_info " +
                "WHERE date_send = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(2,  dateSend);
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<ShippingInfo> getAll() throws SQLException {
        List<ShippingInfo> allShippingInfo = new ArrayList<>();
        String query = "SELECT * " +
                "FROM shipping_info";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int idOfficeStart= resultSet.getInt("id_office_start");
                int idOfficeEnd= resultSet.getInt("id_office_end");
                String dateSend = resultSet.getString("date_send");

                ShippingInfo shippingInfo = ShippingInfoFactory.createShippingInfo(idOfficeStart, idOfficeEnd, dateSend);

                allShippingInfo.add(shippingInfo);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT(all) query: " + e.getMessage());
        }
        return allShippingInfo;
    }
}