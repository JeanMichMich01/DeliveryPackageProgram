package za.ac.cput.repository;

import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.util.Server;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements IAddressRepository{

    @Override
    public Address create(Address address) throws SQLException {
        String query = "INSERT INTO address " +
                "(number_street," +
                " name_street," +
                " zip_code," +
                " name_city) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, address.getNumberStreet());
            pstmt.setString(2, address.getNameStreet());
            pstmt.setString(3, address.getZipCode());
            pstmt.setString(4, address.getNameCity());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return address;
    }


    @Override
    public Address read(String nameCity) throws SQLException {
        String query = "SELECT * " +
                "FROM address " +
                "WHERE name_city = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, nameCity);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int numberStreet = resultSet.getInt("number_street");
                    String nameStreet = resultSet.getString("name_street");
                    String zipCode = resultSet.getString("zip_code");

                    return AddressFactory.createAddress(numberStreet, nameStreet, zipCode, nameCity);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Address update(Address address) throws SQLException {
        String query = "UPDATE address " +
                "SET number_street = ?, " +
                "name_street = ?, " +
                "zip_code = ? " +
                "WHERE name_city = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, address.getNumberStreet());
            pstmt.setString(2, address.getNameStreet());
            pstmt.setString(3, address.getZipCode());
            pstmt.setString(4, address.getNameCity());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return address;
    }


    @Override
    public boolean delete(String nameCity) throws SQLException {
        String query = "DELETE FROM address " +
                "WHERE name_city = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, nameCity);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<Address> getAll() throws SQLException {
        List<Address> allAddress = new ArrayList<>();
        String query = "SELECT * " +
                "FROM address";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int numberStreet = resultSet.getInt("number_street");
                String nameStreet = resultSet.getString("name_street");
                String zipCode = resultSet.getString("zip_code");
                String nameCity = resultSet.getString("name_city");

                Address address =  AddressFactory.createAddress(numberStreet, nameStreet, zipCode, nameCity);

                allAddress.add(address);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT(all) query: " + e.getMessage());
        }
        return allAddress;
    }
}

