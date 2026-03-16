package model.service;
import model.entity.Bakery;
import model.entity.builder.BakeryBuilder;
import model.repository.BakeryRepository;
import model.repository.BakeryRepositoryImpl;

import java.util.List;

public class BakeryService {
    private final BakeryRepository bakeryRepository;

    public BakeryService() {
        bakeryRepository = new BakeryRepositoryImpl();
    }

    public void addBakery(String name, String address) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Bakery name cannot be empty");
        }

        Bakery bakery = new BakeryBuilder()
                .setName(name)
                .setAddress(address)
                .build();
        bakeryRepository.add(bakery);

        bakeryRepository.add(bakery);
    }

    public void updateBakery(int id, String name, String address) {

        Bakery bakery = new BakeryBuilder()
                .setId(id)
                .setName(name)
                .setAddress(address)
                .build();
        bakeryRepository.add(bakery);
        bakeryRepository.update(bakery);
    }

    public void deleteBakery(int id) {
        bakeryRepository.delete(id);
    }

    public List<Bakery> getAllBakeries() {
        return bakeryRepository.findAll();
    }

    public Bakery getBakeryById(int id) {
        return bakeryRepository.findById(id);
    }
}
