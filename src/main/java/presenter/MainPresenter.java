package presenter;

import model.entity.Bakery;
import model.entity.Cake;
import model.entity.CakeStock;
import model.repository.BakeryRepository;
import model.repository.BakeryRepositoryImpl;
import model.repository.CakeRepository;
import model.repository.CakeRepositoryImpl;
import model.repository.CakeStockRepository;
import model.repository.CakeStockRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private final BakeryRepository bakeryRepository;
    private final CakeRepository cakeRepository;
    private final CakeStockRepository cakeStockRepository;
    private final MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
        this.bakeryRepository = new BakeryRepositoryImpl();
        this.cakeRepository = new CakeRepositoryImpl();
        this.cakeStockRepository = new CakeStockRepositoryImpl();
    }

    public void loadBakeries() {
        List<Bakery> bakeries = bakeryRepository.findAll();
        List<String> result = new ArrayList<>();

        for (Bakery bakery : bakeries) {
            result.add(bakery.getId() + " | " + bakery.getName() + " | " + bakery.getAddress());
        }

        view.showBakeries(result);
    }

    public void addBakery(String name, String address) {
        if (name == null || name.isBlank()) {
            view.showError("Bakery name cannot be empty!");
            return;
        }

        Bakery bakery = new Bakery();
        bakery.setName(name);
        bakery.setAddress(address);

        bakeryRepository.add(bakery);
        loadBakeries();
        view.showMessage("Bakery added successfully.");
    }

    public void updateBakery(String idText, String name, String address) {
        try {
            int id = Integer.parseInt(idText);

            Bakery bakery = new Bakery();
            bakery.setId(id);
            bakery.setName(name);
            bakery.setAddress(address);

            bakeryRepository.update(bakery);
            loadBakeries();
            view.showMessage("Bakery updated successfully.");
        } catch (NumberFormatException e) {
            view.showError("Bakery id must be a number!");
        }
    }

    public void deleteBakery(String idText) {
        try {
            int id = Integer.parseInt(idText);
            bakeryRepository.delete(id);
            loadBakeries();
            view.showMessage("Bakery deleted successfully.");
        } catch (NumberFormatException e) {
            view.showError("Bakery id must be a number!");
        }
    }

    public void loadCakesSorted() {
        List<Cake> cakes = cakeRepository.findAllSortedByName();
        List<String> result = new ArrayList<>();

        for (Cake cake : cakes) {
            result.add(cake.getId() + " | " + cake.getName() + " | " + cake.getPrice() + " | " + cake.getImagePath());
        }

        view.showCakes(result);
    }

    public void addCakeWithStock(String name,
                                 String priceText,
                                 String imagePath,
                                 String bakeryIdText,
                                 String quantityText,
                                 String expiryDateText,
                                 String availableText) {
        try {
            double price = Double.parseDouble(priceText);
            int bakeryId = Integer.parseInt(bakeryIdText);
            int quantity = Integer.parseInt(quantityText);
            LocalDate expiryDate = LocalDate.parse(expiryDateText);
            boolean available = Boolean.parseBoolean(availableText);

            Cake cake = new Cake();
            cake.setName(name);
            cake.setPrice(price);
            cake.setImagePath(imagePath);

            int generatedCakeId = cakeRepository.add(cake);

            if (generatedCakeId == -1) {
                view.showError("Cake could not be added.");
                return;
            }

            CakeStock stock = new CakeStock();
            stock.setCakeId(generatedCakeId);
            stock.setBakeryId(bakeryId);
            stock.setQuantity(quantity);
            stock.setExpiryDate(expiryDate);
            stock.setAvailable(available);

            cakeStockRepository.add(stock);

            loadCakesSorted();
            view.showMessage("Cake and stock added successfully.");

        } catch (Exception e) {
            view.showError("Invalid input! Check price, bakery id, quantity, date format yyyy-MM-dd, available true/false.");
        }
    }

    public void updateCake(String idText, String name, String priceText, String imagePath) {
        try {
            int id = Integer.parseInt(idText);
            double price = Double.parseDouble(priceText);

            Cake cake = new Cake();
            cake.setId(id);
            cake.setName(name);
            cake.setPrice(price);
            cake.setImagePath(imagePath);

            cakeRepository.update(cake);
            loadCakesSorted();
            view.showMessage("Cake updated successfully.");
        } catch (NumberFormatException e) {
            view.showError("Cake id and price must be valid numbers!");
        }
    }

    public void deleteCake(String idText) {
        try {
            int id = Integer.parseInt(idText);

            Cake cake = new Cake();
            cake.setId(id);

            cakeRepository.delete(cake);
            loadCakesSorted();
            view.showMessage("Cake deleted successfully.");
        } catch (NumberFormatException e) {
            view.showError("Cake id must be a number!");
        }
    }

    public void searchCakeByName(String name) {
        if (name == null || name.isBlank()) {
            view.showError("Cake name cannot be empty!");
            return;
        }

        Cake cake = cakeRepository.findByName(name);
        List<String> result = new ArrayList<>();

        if (cake != null) {
            result.add(cake.getId() + " | " + cake.getName() + " | " + cake.getPrice() + " | " + cake.getImagePath());
        } else {
            view.showError("Cake not found.");
        }

        view.showCakes(result);
    }

    public void filterAvailableCakesByBakery(String bakeryIdText) {
        try {
            int bakeryId = Integer.parseInt(bakeryIdText);
            List<CakeStock> stocks = cakeStockRepository.findAvailable(bakeryId);
            List<String> result = new ArrayList<>();

            for (CakeStock stock : stocks) {
                result.add(
                        stock.getId() + " | CakeId: " + stock.getCakeId() +
                                " | BakeryId: " + stock.getBakeryId() +
                                " | Qty: " + stock.getQuantity() +
                                " | Exp: " + stock.getExpiryDate() +
                                " | Available: " + stock.isAvailable()
                );
            }

            view.showStocks(result);
        } catch (NumberFormatException e) {
            view.showError("Bakery id must be a number!");
        }
    }

    public void filterValidCakesByBakery(String bakeryIdText) {
        try {
            int bakeryId = Integer.parseInt(bakeryIdText);
            List<CakeStock> stocks = cakeStockRepository.findByBakery(bakeryId);
            List<String> result = new ArrayList<>();
            LocalDate today = LocalDate.now();

            for (CakeStock stock : stocks) {
                if (stock.getExpiryDate() != null && !stock.getExpiryDate().isBefore(today)) {
                    result.add(
                            stock.getId() + " | CakeId: " + stock.getCakeId() +
                                    " | BakeryId: " + stock.getBakeryId() +
                                    " | Qty: " + stock.getQuantity() +
                                    " | Exp: " + stock.getExpiryDate() +
                                    " | Available: " + stock.isAvailable()
                    );
                }
            }

            view.showStocks(result);
        } catch (NumberFormatException e) {
            view.showError("Bakery id must be a number!");
        }
    }



}