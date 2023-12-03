package za.ac.cput.repository;

import za.ac.cput.domain.Package;
import za.ac.cput.domain.StatuePackage;
import za.ac.cput.factory.PackageFactory;
import za.ac.cput.util.Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageRepository implements IPackageRepository {

    @Override
    public Package create(Package pack) throws SQLException {
        String query = "INSERT INTO package " +
                "(id_package," +
                " weight," +
                " fragile," +
                " status) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, pack.getIdPackage());
            pstmt.setInt(2, pack.getWeight());
            pstmt.setBoolean(3, pack.isFragile());
            pstmt.setString(4, String.valueOf(StatuePackage.InTransit));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute INSERT query: " + e.getMessage());
        }
        return pack;
    }

    @Override
    public Package read(Integer idPackage) throws SQLException {
        String query = "SELECT * " +
                "FROM package " +
                "WHERE id_package = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, idPackage);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int weight = resultSet.getInt("weight");
                    boolean fragile = resultSet.getBoolean("fragile");
                    String statusString = resultSet.getString("status");
                    StatuePackage status = StatuePackage.valueOf(statusString);

                    return PackageFactory.createPackage(idPackage, weight, fragile, status);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT query: " + e.getMessage());
        }
        return null;
    }


    @Override
    public Package update(Package pack) throws SQLException {
        String query = "UPDATE package " +
                "SET weight = ?, " +
                "fragile = ?, " +
                "status = ? " +
                "WHERE id_package = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, pack.getWeight());
            pstmt.setBoolean(2, pack.isFragile());
            pstmt.setString(3, pack.getStatus().toString());
            pstmt.setInt(4, pack.getIdPackage());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to execute UPDATE query: " + e.getMessage());
        }
        return pack;
    }

    @Override
    public boolean delete(Integer idPackage) throws SQLException {
        String query = "DELETE FROM package " +
                "WHERE id_package = ?";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, idPackage);
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to execute DELETE query: " + e.getMessage());
        }
    }

    @Override
    public List<Package> getAll() throws SQLException {
        List<Package> allPackages = new ArrayList<>();
        String query = "SELECT * " +
                "FROM package";
        try (PreparedStatement pstmt = Server.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int idPackage = resultSet.getInt("id_package");
                int weight = resultSet.getInt("weight");
                boolean fragile = resultSet.getBoolean("fragile");
                String statusString = resultSet.getString("status");
                StatuePackage status = StatuePackage.valueOf(statusString);

                Package pack = PackageFactory.createPackage(idPackage, weight, fragile, status);
                allPackages.add(pack);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to execute SELECT(all) query: " + e.getMessage());
        }
        return allPackages;
    }
}