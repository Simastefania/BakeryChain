package model.repository;

import model.domain.CakeStock;

import java.util.List;

public interface CakeStockRepository {
    void add(CakeStock stock);
    void update(CakeStock stock);
    void delete(int id);
    List<CakeStock> findAll();
    CakeStock findById(int id);

    List<CakeStock> findByBakery(int bakeryId);
    List<CakeStock> findByCake(int cakeId);
    List<CakeStock> findAvailable(int bakeryId);
}