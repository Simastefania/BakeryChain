package model.repository;

import model.entity.CakeStock;

import java.util.List;

public interface CakeStockRepository {

    List<CakeStock> findByBakery(int bakeryId);
    List<CakeStock> findByCake(int cakeId);
    List<CakeStock> findAvailable(int bakeryId);
}
