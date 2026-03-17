package model.repository;

import model.entity.Bakery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BakeryRepositoryImpl implements BakeryRepository {
    @Override
    public void add(Bakery bakery) {

        String sql = "INSERT INTO Bakery(name, address) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bakery.getName());
            stmt.setString(2, bakery.getAddress());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Bakery bakery) {

        String sql = "UPDATE Bakery SET name=?, address=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bakery.getName());
            stmt.setString(2, bakery.getAddress());
            stmt.setInt(3, bakery.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM Bakery WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bakery> findAll() {

        List<Bakery> bakeries = new ArrayList<>();

        String sql = "SELECT * FROM Bakery";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Bakery bakery = new Bakery();
                bakery.setId(rs.getInt("id"));
                bakery.setName(rs.getString("name"));
                bakery.setAddress(rs.getString("address"));

                bakeries.add(bakery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bakeries;
    }

    @Override
    public Bakery findById(int id) {

        String sql = "SELECT * FROM Bakery WHERE id=?";
        Bakery bakery = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bakery = new Bakery();
                bakery.setId(rs.getInt("id"));
                bakery.setName(rs.getString("name"));
                bakery.setAddress(rs.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bakery;
    }
}
