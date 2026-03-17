package model.repository;

import model.entity.CakeStock;


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

                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setId_cake(rs.getInt("id_cake"));
                stock.setId_bakery(rs.getInt("id_bakery"));
                stock.setQuantity(rs.getInt("quantity"));
                stock.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
                stock.setAvailable(rs.getBoolean("available"));

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

                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setId_cake(rs.getInt("id_cake"));
                stock.setId_bakery(rs.getInt("id_bakery"));
                stock.setQuantity(rs.getInt("quantity"));
                stock.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
                stock.setAvailable(rs.getBoolean("available"));

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

                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setId_cake(rs.getInt("id_cake"));
                stock.setId_bakery(rs.getInt("id_bakery"));
                stock.setQuantity(rs.getInt("quantity"));
                stock.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
                stock.setAvailable(rs.getBoolean("available"));

                stocks.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }


}