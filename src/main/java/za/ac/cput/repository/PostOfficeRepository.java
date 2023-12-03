package za.ac.cput.repository;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.PostOffice;
import za.ac.cput.factory.PostOfficeFactory;
import za.ac.cput.util.Server;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostOfficeRepository implements IPostOfficeRepository{

    @Override
    public PostOffice create(PostOffice postOffice) throws SQLException {
        String query = "INSERT INTO post_office " +
                "(name_post_office," +
                " max_capacity," +
                " address)" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, postOffice.getNamePostOffice());
            pstmt.setInt(2, postOffice.getMaxCapacity());
            pstmt.setString(3, postOffice.getAddress().getNameCity());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return postOffice;
    }

    @Override
    public PostOffice read(String idPostOffice) throws SQLException {
        String query = "SELECT * " +
                "FROM post_office " +
                "WHERE id_post_office = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, idPostOffice);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String namePostOffice = resultSet.getString("name_post_office");
                    int maxCapacity = resultSet.getInt("max_capacity");
                    String cityName = resultSet.getString("address");

                    AddressRepository addressRepository = new AddressRepository();
                    Address address = addressRepository.read(cityName);

                    return PostOfficeFactory.createPostOffice(Integer.parseInt(idPostOffice), namePostOffice, maxCapacity, address);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }

    @Override
    public PostOffice update(PostOffice postOffice) throws SQLException {
        String query = "UPDATE post_office " +
                "SET name_post_office = ?, " +
                "max_capacity = ? " +
                "WHERE id_post_office = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setString(1, postOffice.getNamePostOffice());
            pstmt.setInt(2, postOffice.getMaxCapacity());
            pstmt.setInt(3, postOffice.getIdPostOffice());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return postOffice;
    }

    @Override
    public boolean delete(String idPostOffice) throws SQLException {
        String query = "DELETE FROM post_office " +
                "WHERE id_post_office = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(idPostOffice));

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<PostOffice> getAll() throws SQLException {
        List<PostOffice> allPostOffices = new ArrayList<>();
        String query = "SELECT * " +
                "FROM post_office";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int idPostOffice = resultSet.getInt("id_post_office");
                String namePostOffice = resultSet.getString("name_post_office");
                int maxCapacity = resultSet.getInt("max_capacity");
                String cityName = resultSet.getString("address");

                AddressRepository addressRepository = new AddressRepository();
                Address address = addressRepository.read(cityName);

                PostOffice postOffice = PostOfficeFactory.createPostOffice(idPostOffice, namePostOffice, maxCapacity, address);
                allPostOffices.add(postOffice);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT(all) query: " + e.getMessage());
        }
        return allPostOffices;
    }
}
