package model.service;
import model.entity.CakeStock;
import model.repository.CakeStockRepository;
import model.repository.CakeStockRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CakeStockService {

    private final CakeStockRepository stockRepository;

    public CakeStockService() {
        stockRepository = new CakeStockRepositoryImpl();
    }

    public List<CakeStock> getStockByBakery(int bakeryId) {
        return stockRepository.findByBakery(bakeryId);
    }

    public List<CakeStock> getAvailableCakes(int bakeryId) {
        return stockRepository.findAvailable(bakeryId);
    }

    public List<CakeStock> getValidCakes(int bakeryId) {

        List<CakeStock> stocks = stockRepository.findByBakery(bakeryId);

        return stocks.stream()
                .filter(stock -> stock.getExpiryDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<CakeStock> getStockByCake(int cakeId) {
        return stockRepository.findByCake(cakeId);
    }
}