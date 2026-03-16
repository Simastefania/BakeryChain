package model.repository;

import model.entity.Bakery;

import java.util.List;

public interface BakeryRepository {

    void add(Bakery bakery);

    void update(Bakery bakery);

    void delete(int id);

    List<Bakery> findAll();

    Bakery findById(int id);
}
