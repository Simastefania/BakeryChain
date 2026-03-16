package model.repository;

import model.database.DatabaseConnection;
import model.entity.CakeStock;
import model.entity.builder.CakeStockBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CakeStockRepositoryImpl implements CakeStockRepository {

    @Override
    public List<CakeStock> findByBakery(int bakeryId) {

        List<CakeStock> stocks = new ArrayList<>();

        String sql = "SELECT * FROM CakeStock WHERE id_bakery=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bakeryId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CakeStock stock = new CakeStockBuilder()
                        .setId(rs.getInt("id"))
                        .setIdCake(rs.getInt("id_cake"))
                        .setIdBakery(rs.getInt("id_bakery"))
                        .setQuantity(rs.getInt("quantity"))
                        .setExpiryDate(rs.getDate("expiry_date").toLocalDate())
                        .setAvailable(rs.getBoolean("available"))
                        .build();

                stocks.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }

    @Override
    public List<CakeStock> findByCake(int cakeId) {

        List<CakeStock> stocks = new ArrayList<>();

        String sql = "SELECT * FROM CakeStock WHERE id_cake=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cakeId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CakeStock stock = new CakeStockBuilder()
                        .setId(rs.getInt("id"))
                        .setIdCake(rs.getInt("id_cake"))
                        .setIdBakery(rs.getInt("id_bakery"))
                        .setQuantity(rs.getInt("quantity"))
                        .setExpiryDate(rs.getDate("expiry_date").toLocalDate())
                        .setAvailable(rs.getBoolean("available"))
                        .build();

                stocks.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }

    @Override
    public List<CakeStock> findAvailable(int bakeryId) {

        List<CakeStock> stocks = new ArrayList<>();

        String sql = "SELECT * FROM CakeStock WHERE id_bakery=? AND available=true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bakeryId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CakeStock stock = new CakeStockBuilder()
                        .setId(rs.getInt("id"))
                        .setIdCake(rs.getInt("id_cake"))
                        .setIdBakery(rs.getInt("id_bakery"))
                        .setQuantity(rs.getInt("quantity"))
                        .setExpiryDate(rs.getDate("expiry_date").toLocalDate())
                        .setAvailable(rs.getBoolean("available"))
                        .build();

                stocks.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }


}