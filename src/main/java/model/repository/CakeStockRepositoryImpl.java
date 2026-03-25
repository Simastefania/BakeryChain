package model.repository;

import model.entity.CakeStock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CakeStockRepositoryImpl implements CakeStockRepository {

    @Override
    public void add(CakeStock stock) {
        String sql = "INSERT INTO cake_stock(id_cake, id_bakery, quantity, expiry_date, available) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stock.getCakeId());
            stmt.setInt(2, stock.getBakeryId());
            stmt.setInt(3, stock.getQuantity());
            stmt.setDate(4, Date.valueOf(stock.getExpiryDate()));
            stmt.setBoolean(5, stock.isAvailable());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CakeStock stock) {
        String sql = "UPDATE cake_stock SET id_cake=?, id_bakery=?, quantity=?, expiry_date=?, available=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stock.getCakeId());
            stmt.setInt(2, stock.getBakeryId());
            stmt.setInt(3, stock.getQuantity());
            stmt.setDate(4, Date.valueOf(stock.getExpiryDate()));
            stmt.setBoolean(5, stock.isAvailable());
            stmt.setInt(6, stock.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cake_stock WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CakeStock> findAll() {
        List<CakeStock> stocks = new ArrayList<>();

        String sql = "SELECT * FROM cake_stock";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setCakeId(rs.getInt("id_cake"));
                stock.setBakeryId(rs.getInt("id_bakery"));
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
    public CakeStock findById(int id) {
        String sql = "SELECT * FROM cake_stock WHERE id=?";
        CakeStock stock = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setCakeId(rs.getInt("id_cake"));
                stock.setBakeryId(rs.getInt("id_bakery"));
                stock.setQuantity(rs.getInt("quantity"));
                stock.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
                stock.setAvailable(rs.getBoolean("available"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stock;
    }

    @Override
    public List<CakeStock> findByBakery(int bakeryId) {
        List<CakeStock> stocks = new ArrayList<>();

        String sql = "SELECT * FROM cake_stock WHERE id_bakery=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bakeryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setCakeId(rs.getInt("id_cake"));
                stock.setBakeryId(rs.getInt("id_bakery"));
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

        String sql = "SELECT * FROM cake_stock WHERE id_cake=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cakeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setCakeId(rs.getInt("id_cake"));
                stock.setBakeryId(rs.getInt("id_bakery"));
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

        String sql = "SELECT * FROM cake_stock WHERE id_bakery=? AND available=true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bakeryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CakeStock stock = new CakeStock();
                stock.setId(rs.getInt("id"));
                stock.setCakeId(rs.getInt("id_cake"));
                stock.setBakeryId(rs.getInt("id_bakery"));
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