package model.repository;

import model.database.DatabaseConnection;
import model.entity.Cake;
import model.entity.builder.CakeBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CakeRepositoryImpl implements CakeRepository {

    @Override
    public void add(Cake cake) {

        String sql = "INSERT INTO Cake(name, price, image_path) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cake.getName());
            stmt.setDouble(2, cake.getPrice());
            stmt.setString(3, cake.getImagePath());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cake cake) {

        String sql = "UPDATE Cake SET name=?, price=?, image_path=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cake.getName());
            stmt.setDouble(2, cake.getPrice());
            stmt.setString(3, cake.getImagePath());
            stmt.setInt(4, cake.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cake cake) {

        String sql = "DELETE FROM Cake WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cake.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cake> findAll() {

        List<Cake> cakes = new ArrayList<>();

        String sql = "SELECT * FROM Cake";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Cake cake = new CakeBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price"))
                        .setImagePath(rs.getString("image_path"))
                        .build();

                cakes.add(cake);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cakes;
    }

    @Override
    public List<Cake> findAllSortedByName() {

        List<Cake> cakes = new ArrayList<>();

        String sql = "SELECT * FROM Cake ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Cake cake = new CakeBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price"))
                        .setImagePath(rs.getString("image_path"))
                        .build();

                cakes.add(cake);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cakes;
    }

    @Override
    public Cake findByName(String name) {

        String sql = "SELECT * FROM Cake WHERE name=?";
        Cake cake = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                cake = new CakeBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price"))
                        .setImagePath(rs.getString("image_path"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cake;
    }
}