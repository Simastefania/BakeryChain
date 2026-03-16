package model.service;
import model.entity.Cake;
import model.entity.builder.CakeBuilder;
import model.repository.CakeRepository;
import model.repository.CakeRepositoryImpl;

import java.util.List;
public class CakeService {
    private final CakeRepository cakeRepository;

    public CakeService() {
        cakeRepository = new CakeRepositoryImpl();
    }

    public void addCake(String name, double price, String imagePath) {

        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        Cake cake = new CakeBuilder()
                .setName(name)
                .setPrice(price)
                .setImagePath(imagePath)
                .build();

        cakeRepository.add(cake);
    }

    public void updateCake(int id, String name, double price, String imagePath) {

        Cake cake = new CakeBuilder()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setImagePath(imagePath)
                .build();

        cakeRepository.update(cake);
    }

    public void deleteCake(Cake cake) {
        cakeRepository.delete(cake);
    }

    public List<Cake> getAllCakes() {
        return cakeRepository.findAll();
    }

    public List<Cake> getAllCakesSorted() {
        return cakeRepository.findAllSortedByName();
    }

    public Cake findCakeByName(String name) {
        return cakeRepository.findByName(name);
    }
}
